package com.away1994.lang.symbols.impl;

import com.away1994.common.utils.log.LogUtils;
import com.away1994.lang.io.seriablize.serializer.*;
import com.away1994.lang.parser.Type;
import com.away1994.lang.symbols.Class;
import com.away1994.lang.symbols.File;
import com.away1994.lang.symbols.Interface;
import com.away1994.lang.symbols.Symbol;
import com.away1994.lang.symbols.variable.Variable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.away1994.common.constants.log.ErrorConstants.OWNER_TYPE_ERROR;
import static com.away1994.dist.constants.CommonConstants.LINE_SEPARATOR;

public class FileImpl extends SymbolImpl implements File {

    private static final transient Logger LOGGER = Logger.getLogger(FileImpl.class.getName());

    public FileImpl() {
    }

    public FileImpl(String name, Symbol owner) {
        super(name, owner);
    }

    /**
     * owner always path
     */


    /**
     * classes in the file
     */
    @JsonDeserialize(contentUsing = ClassDeserializer.class)
    @JsonSerialize(contentUsing = ClassSerializer.class)
    public ArrayList<Class> classes = new ArrayList<>();

    /**
     * interface in the file
     */
    @JsonDeserialize(contentUsing = InterfaceDeserializer.class)
    @JsonSerialize(contentUsing = InterfaceSerializer.class)
    public ArrayList<Interface> interfaces = new ArrayList<>();

    /**
     * variables in the file
     */
    @JsonDeserialize(contentUsing = VariableDeserializer.class)
    @JsonSerialize(contentUsing = VariableSerializer.class)
    public ArrayList<Variable> variables = new ArrayList<>();

    /**
     * other symbols
     */
    @JsonIgnore
    public ArrayList<Symbol> otherSymbols = new ArrayList<>();

    /**
     *
     * @return extension of file
     */
    @JsonIgnore
    public String getExtension() {
        int lastIndex = this.getName().lastIndexOf(".");
        return this.getName().substring(lastIndex + 1);
    }

    @JsonIgnore
    public String getFullPath() {

        if (owner instanceof PathImpl) {
            return ((PathImpl) this.owner).getPath() + "/" + this.name;
        } else {
            LOGGER.log(Level.SEVERE, LogUtils.buildLogString(OWNER_TYPE_ERROR, this.name));
            return null;
        }

    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }

    public ArrayList<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(ArrayList<Interface> interfaces) {
        this.interfaces = interfaces;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Variable> variables) {
        this.variables = variables;
    }

    public String description() {
        StringBuilder sb = new StringBuilder();


        sb.append("name:");
        sb.append(LINE_SEPARATOR);
        sb.append(this.name);
        sb.append(LINE_SEPARATOR);

        sb.append("owner:");
        sb.append(LINE_SEPARATOR);
        sb.append(this.owner.identify());
        sb.append(LINE_SEPARATOR);

        return sb.toString();
    }

    @Override
    public Type state() {
        return Type.FILE_STATE;
    }


    @Override
    public Collection<Symbol> allSymbols() {
        ArrayList<Symbol> symbols = new ArrayList<>();
        symbols.addAll(classes);
        symbols.addAll(interfaces);
        symbols.addAll(variables);
        return symbols;
    }

}
