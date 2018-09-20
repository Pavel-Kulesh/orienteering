package com.itacademy.jd2.pk.hop.dao.jdbc.impl.util;

import java.sql.SQLException;
import java.sql.Statement;

@FunctionalInterface
public interface StatemenAction<RETURN_TYPE> {

    RETURN_TYPE doWithStatement(Statement stmt) throws SQLException;


}
