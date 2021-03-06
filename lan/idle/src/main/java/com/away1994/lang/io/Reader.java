package com.away1994.lang.io;

import com.away1994.lang.parser.Type;
import com.away1994.lang.symbols.Symbol;

import java.io.File;
import java.util.Collection;

public interface Reader {

    /**
     * 只读最高层级的symbol
     * @param name name of symbol
     * @param type type of symbol
     * @return a collection of symbols
     */
    public <T extends Symbol> Collection<T> getSymbols(String name, Type type);

    public <T extends Symbol> T getSymbol(T symbol);

    public <T extends Symbol> Collection<T> getSymbols(Type type);

    public <T extends Symbol> T getSymbol(String identify);

    public <T extends Symbol> File getSymbolFile(T s);

    public <T extends Symbol> Boolean isSymbolExist(T s);
}
