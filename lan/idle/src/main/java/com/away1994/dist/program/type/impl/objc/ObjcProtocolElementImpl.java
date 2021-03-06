package com.away1994.dist.program.type.impl.objc;

import com.away1994.dist.feature.plantuml.classes.element.ClassesDiagramElement;
import com.away1994.dist.feature.plantuml.classes.element.impl.CDEClassImpl;
import com.away1994.dist.feature.plantuml.classes.element.impl.CDEInterfaceImpl;
import com.away1994.dist.feature.plantuml.classes.relation.ClassesDiagramRelation;
import com.away1994.dist.feature.plantuml.classes.relation.impl.CDRImplementImpl;
import com.away1994.gen.objectivec.ObjectiveCParser;
import com.away1994.dist.program.method.MethodElement;
import com.away1994.dist.program.method.MethodTag;
import com.away1994.dist.program.method.impl.objc.ObjcMethodElementImpl;
import com.away1994.dist.program.type.AbstractTypeElement;
import com.away1994.dist.program.type.InterfaceElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ObjcProtocolElementImpl extends AbstractTypeElement implements InterfaceElement {

    ObjectiveCParser.ProtocolDeclarationContext protocolDeclarationContext;


    public ObjcProtocolElementImpl(String name) {
        super(name);
    }

    public void setProtocolDeclarationContext(ObjectiveCParser.ProtocolDeclarationContext protocolDeclarationContext) {
        this.protocolDeclarationContext = protocolDeclarationContext;
    }

    public ObjectiveCParser.ProtocolDeclarationContext getProtocolDeclarationContext() {
        return protocolDeclarationContext;
    }

    @Override
    public Set<InterfaceElement> getExtendInterfaces() {
        Set<InterfaceElement> elements = new HashSet<>();
        if (protocolDeclarationContext.protocolReferenceList() != null) {
            for (ObjectiveCParser.ProtocolNameContext name : protocolDeclarationContext.protocolReferenceList().protocolList().protocolName()) {
                String protocol = name.getText();
                elements.add(new ObjcProtocolElementImpl(protocol));
            }
        }

        return elements;
    }

    @Override
    public Set<MethodElement> getDeclareMethods() {
        Set<MethodElement> elements = new HashSet<>();
        List<ObjectiveCParser.InterfaceDeclarationListContext> declarationListContexts = protocolDeclarationContext.interfaceDeclarationList();
        if (declarationListContexts != null) {
            for (ObjectiveCParser.InterfaceDeclarationListContext declarationListContext : declarationListContexts) {


                List<ObjectiveCParser.ClassMethodDeclarationContext> methodList = declarationListContext.classMethodDeclaration();
                if (methodList != null) {
                    for (ObjectiveCParser.ClassMethodDeclarationContext method : methodList) {
                        String methodName = method.methodDeclaration().methodSelector().getText();
                        TreeSet<MethodTag> tags = new TreeSet<>();
                        tags.add(MethodTag.PUBLIC);
                        elements.add(new ObjcMethodElementImpl(method, tags));
                    }
                }

                List<ObjectiveCParser.InstanceMethodDeclarationContext> instanceMethodList = declarationListContext.instanceMethodDeclaration();
                if (instanceMethodList != null) {
                    for (ObjectiveCParser.InstanceMethodDeclarationContext method : instanceMethodList) {
                        TreeSet<MethodTag> tags = new TreeSet<>();
                        tags.add(MethodTag.PUBLIC);
                        elements.add(new ObjcMethodElementImpl(method, tags));
                    }
                }
            }
        }
        return elements;
    }

    @Override
    public Set<ClassesDiagramElement> getClassesDiagramElements() {
        Set<ClassesDiagramElement> elements = new HashSet<>();
        {
            ClassesDiagramElement e = new CDEInterfaceImpl(this.getName());
            for (MethodElement method : this.getDeclareMethods()) {
                e.addItem(method.getClassesDiagramItem());
            }
            elements.add(e);
        }
        return elements;
    }

    @Override
    public Set<ClassesDiagramRelation> getClassesDiagramRelations() {
        Set<ClassesDiagramRelation> elements = new HashSet<>();

        for (InterfaceElement i : this.getExtendInterfaces()) {
            elements.add(new CDRImplementImpl(new CDEClassImpl(this.getName()), new CDEInterfaceImpl(i.getName())));
        }

        return elements;
    }
}
