package CRUD.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DatabaseConnectionService {

    @Autowired
    private final DataSource dataSource;

    @Autowired
    public DatabaseConnectionService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isDatabaseConnected() {
        try (Connection connection = dataSource.getConnection()) {
            return true; // Connection successful
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception or log it
            return false; // Connection failed
        }
    }
}
