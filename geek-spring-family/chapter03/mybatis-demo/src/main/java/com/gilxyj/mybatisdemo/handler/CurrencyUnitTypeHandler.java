package com.gilxyj.mybatisdemo.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.joda.money.CurrencyUnit;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: chapter03
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-19 03:03
 **/
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(CurrencyUnit.class)
public class CurrencyUnitTypeHandler extends BaseTypeHandler<CurrencyUnit> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, CurrencyUnit currencyUnit, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,currencyUnit.getCode());
    }

    @Override
    public CurrencyUnit getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String code = resultSet.getString(s);
        return getCurrencyUnit(code);
    }

    @Override
    public CurrencyUnit getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String code = resultSet.getString(i);
        return getCurrencyUnit(code);
    }

    @Override
    public CurrencyUnit getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String code = callableStatement.getString(i);
        return getCurrencyUnit(code);
    }

    private CurrencyUnit getCurrencyUnit(String code){
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        return CurrencyUnit.of(code);
    }
}
