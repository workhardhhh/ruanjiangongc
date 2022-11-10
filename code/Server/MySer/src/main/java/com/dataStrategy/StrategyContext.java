package com.dataStrategy;

import com.Interface.DataStrategy;
import com.tool.Constant;

import java.util.List;

public class StrategyContext<E> {
    private DataStrategy<E> dataStrategy;
    private E sql;



    public void setContext(DataStrategy dataStrategy)
    {
        this.dataStrategy=dataStrategy;
    }
    public void executeSql(int state, DataStrategy dataStrategy) throws Exception {
        switch (state)
        {
            case 0:
                dataStrategy.deleteTable(sql);
                break;
            case 1:
                dataStrategy.delete(sql);
                break;
            case 2:
                dataStrategy.insert(sql);
                break;
            case 3:
                dataStrategy.update(sql);
            case 4:
                dataStrategy.createTable(sql);

        }
    }
    public List<DataStrategy> findAll(DataStrategy dataStrategy) throws Exception {
        List<DataStrategy> list = dataStrategy.findAll(sql);
        return list;
    }
    public E getSql() {
        return sql;
    }

    public void setSql(E sql) {
        this.sql = sql;
    }
}
