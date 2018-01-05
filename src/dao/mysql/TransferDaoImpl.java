package dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import dao.DaoException;
import dao.TransferDao;
import domain.Account;
import domain.Transfer;
import domain.User;

public class TransferDaoImpl extends BaseDaoImpl implements TransferDao {
    @Override
    public Transfer read(Long id) throws DaoException {
        String sql = "SELECT `source_id`, `destination_id`, `amount`, `date`, `operator_id` FROM `transfer` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Transfer transfer = null;
            if(resultSet.next()) {
                transfer = new Transfer();
                transfer.setId(id);
                id = resultSet.getLong("source_id");
                if(!resultSet.wasNull()) {
                    transfer.setSource(new Account());
                    transfer.getSource().setId(id);
                }
                id = resultSet.getLong("destination_id");
                if(!resultSet.wasNull()) {
                    transfer.setDestination(new Account());
                    transfer.getDestination().setId(id);
                }
                transfer.setAmount(resultSet.getLong("amount"));
                transfer.setDate(new java.util.Date(resultSet.getTimestamp("date").getTime()));
                transfer.setOperator(new User());
                transfer.getOperator().setId(resultSet.getLong("operator_id"));
            }
            return transfer;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public List<Transfer> readHistoryOfAccount(Long accountId) throws DaoException {
        String sql = "SELECT `id`, `source_id`, `destination_id`, `amount`, `date`, `operator_id` FROM `transfer` WHERE `source_id` = ? OR `destination_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, accountId);
            statement.setLong(2, accountId);
            resultSet = statement.executeQuery();
            List<Transfer> transfers = new ArrayList<>();
            while(resultSet.next()) {
                Transfer transfer = new Transfer();
                transfer.setId(resultSet.getLong("id"));
                accountId = resultSet.getLong("source_id");
                if(!resultSet.wasNull()) {
                    transfer.setSource(new Account());
                    transfer.getSource().setId(accountId);
                }
                accountId = resultSet.getLong("destination_id");
                if(!resultSet.wasNull()) {
                    transfer.setDestination(new Account());
                    transfer.getDestination().setId(accountId);
                }
                transfer.setAmount(resultSet.getLong("amount"));
                transfer.setDate(new java.util.Date(resultSet.getTimestamp("date").getTime()));
                transfer.setOperator(new User());
                transfer.getOperator().setId(resultSet.getLong("operator_id"));
                transfers.add(transfer);
            }
            return transfers;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public Long create(Transfer transfer) throws DaoException {
        String sql = "INSERT INTO `transfer` (`source_id`, `destination_id`, `amount`, `date`, `operator_id`) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if(transfer.getSource() != null) {
                statement.setLong(1, transfer.getSource().getId());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            if(transfer.getDestination() != null) {
                statement.setLong(2, transfer.getDestination().getId());
            } else {
                statement.setNull(2, Types.INTEGER);
            }
            statement.setLong(3, transfer.getAmount());
            statement.setTimestamp(4, new Timestamp(transfer.getDate().getTime()));
            statement.setLong(5, transfer.getOperator().getId());
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
    public void update(Transfer transfer) throws DaoException {
        String sql = "UPDATE `transfer` SET `source_id` = ?, `destination_id` = ?, `amount` = ?, `date` = ?, `operator_id` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            if(transfer.getSource() != null) {
                statement.setLong(1, transfer.getSource().getId());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            if(transfer.getDestination() != null) {
                statement.setLong(2, transfer.getDestination().getId());
            } else {
                statement.setNull(2, Types.INTEGER);
            }
            statement.setLong(3, transfer.getAmount());
            statement.setTimestamp(4, new Timestamp(transfer.getDate().getTime()));
            statement.setLong(5, transfer.getOperator().getId());
            statement.setLong(6, transfer.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `transfer` WHERE `id` = ?";
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
