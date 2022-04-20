package books

import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.Int
import kotlin.String
import kotlin.Unit
import norm.ParamSetter
import norm.Query
import norm.RowMapper

public data class AddBookParams(
  public val name: String?,
  public val isbn: String?
)

public class AddBookParamSetter : ParamSetter<AddBookParams> {
  public override fun map(ps: PreparedStatement, params: AddBookParams): Unit {
    ps.setObject(1, params.name)
    ps.setObject(2, params.isbn)
  }
}

public class AddBookRowMapper : RowMapper<AddBookResult> {
  public override fun map(rs: ResultSet): AddBookResult = AddBookResult(
  id = rs.getObject("id") as kotlin.Int,
    name = rs.getObject("name") as kotlin.String,
    isbn = rs.getObject("isbn") as kotlin.String)
}

public class AddBookQuery : Query<AddBookParams, AddBookResult> {
  public override val sql: String = """
      |INSERT INTO books(name, isbn) VALUES(?, ?) RETURNING *;
      |""".trimMargin()

  public override val mapper: RowMapper<AddBookResult> = AddBookRowMapper()

  public override val paramSetter: ParamSetter<AddBookParams> = AddBookParamSetter()
}

public data class AddBookResult(
  public val id: Int,
  public val name: String,
  public val isbn: String
)
