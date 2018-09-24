package domain.books

import play.api.libs.json.Json

case class Chapter(
                    id: String,
                    title: String,
                    sectionIds: List[String]
                  ) extends BookBase {}

object Chapter {
  implicit val chapterFormat = Json.format[Chapter]
}