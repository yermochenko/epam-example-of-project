package dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.AccountDao;
import dao.DaoException;
import domain.Account;

public class AccountDaoImpl extends BaseDaoImpl implements AccountDao {
    @Override
    public Account read(Long id) throws DaoException {
        String sql = "SELECT `client`, `balance` FROM `account` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Account account = null;
            if(resultSet.next()) {
                account = new Account();
                account.setId(id);
                account.setClient(resultSet.getString("client"));
                account.setBalance(resultSet.getLong("balance"));
            }
            return account;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public List<Account> readAll() throws DaoException {
        String sql = "SELECT `id`, `client`, `balance` FROM `account`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Account> accounts = new ArrayList<>();
            while(resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setClient(resultSet.getString("client"));
                account.setBalance(resultSet.getLong("balance"));
                accounts.add(account);
            }
            return accounts;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public Long create(Account account) throws DaoException {
        String sql = "INSERT INTO `account` (`client`, `balance`) VALUES (?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, account.getClient());
            account.setBalance(0L);
            statement.setLong(2, account.getBalance());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void update(Account account) throws DaoException {
        String sql = "UPDATE `account` SET `client` = ?, `balance` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, account.getClient());
            statement.setLong(2, account.getBalance());
            statement.setLong(3, account.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `account` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }
}
