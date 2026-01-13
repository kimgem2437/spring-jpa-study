package dialect;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MySQLDialect extends MySQL8Dialect {
    public MySQLDialect() {
        // MySQL의 group_concat 함수를 JPQL에서 사용할 수 있도록 등록
        this.registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}