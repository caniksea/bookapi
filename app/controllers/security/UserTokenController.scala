package controllers.security

import domain.security.{TokenFailExcerption, UserToken}
import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.security.UserTokenService

import scala.concurrent.ExecutionContext.Implicits.global

class UserTokenController @Inject()
(cc: ControllerComponents) extends AbstractController(cc)  {

  def create: Action[JsValue] = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[UserToken](input).get
      val response = for {
        //        auth <- TokenCheckService.apply.getLoginStatus(request)
        results <- UserTokenService.apply.saveEntity(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case tokenCheckFailed: TokenFailExcerption => Unauthorized
          case otherException: Exception => InternalServerError
        }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[UserToken](input).get
      val response = for {
        //        auth <- TokenCheckService.apply.getToken(request)
        results <- UserTokenService.apply.saveEntity(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case tokenCheckFailed: TokenFailExcerption => Unauthorized
          case otherException: Exception => InternalServerError
        }
  }

  def getEntity(id: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent]=>
      val input = request.body
      val response = for {
        //        auth <- TokenCheckService.apply.getTokenFromParam(request)
        results <- UserTokenService.apply.getEntity(id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case tokenCheckFailed: TokenFailExcerption => Unauthorized
          case otherException: Exception => InternalServerError
        }
  }

  def getEntities: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent]=>
      val input = request.body
      val response = for {
        //        auth <- TokenCheckService.apply.getTokenFromParam(request)
        results <- UserTokenService.apply.getEntities
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case tokenCheckFailed: TokenFailExcerption => Unauthorized
          case otherException: Exception => InternalServerError
        }
  }

  def deleteEntity: Action[JsValue] = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[UserToken](input).get
      val response = for {
        //        auth <- TokenCheckService.apply.getTokenFromParam(request)
        results <- UserTokenService.apply.deleteEntity(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case tokenCheckFailed: TokenFailExcerption => Unauthorized
          case otherException: Exception => InternalServerError
        }
  }
}
