package books

import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.Int
import kotlin.String
import kotlin.Unit
import norm.ParamSetter
import norm.Query
import norm.RowMapper

public class GetBooksParams()

public class GetBooksParamSetter : ParamSetter<GetBooksParams> {
  public override fun map(ps: PreparedStatement, params: GetBooksParams): Unit {
  }
}

public class GetBooksRowMapper : RowMapper<GetBooksResult> {
  public override fun map(rs: ResultSet): GetBooksResult = GetBooksResult(
  id = rs.getObject("id") as kotlin.Int,
    name = rs.getObject("name") as kotlin.String,
    isbn = rs.getObject("isbn") as kotlin.String)
}

public class GetBooksQuery : Query<GetBooksParams, GetBooksResult> {
  public override val sql: String = """
      |SELECT * from books;
      |""".trimMargin()

  public override val mapper: RowMapper<GetBooksResult> = GetBooksRowMapper()

  public override val paramSetter: ParamSetter<GetBooksParams> = GetBooksParamSetter()
}

public data class GetBooksResult(
  public val id: Int,
  public val name: String,
  public val isbn: String
)
