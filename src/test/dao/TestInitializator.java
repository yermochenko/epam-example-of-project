package test.dao;

import util.Connector;

public class TestInitializator {
    public static void init() {
        try {
            Connector.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/epam_project_db?useUnicode=true&characterEncoding=UTF-8", "root", "root");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
