package walletmicroservice;

import org.hibernate.dialect.Dialect;

import java.sql.Types;

/*

Not to be deleted!

 */
public class DBManager extends Dialect {

        public DBManager() {
            registerColumnType(Types.TINYINT, "tinyint");
            registerColumnType(Types.INTEGER, "INT");
            registerColumnType(Types.VARCHAR, "TEXT");
            registerColumnType(Types.REAL, "REAL ");
        }


}
