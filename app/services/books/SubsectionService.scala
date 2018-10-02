/*
 * Copyright (c) 2018/09/29 3:17 PM.
 * Author: caniksea
 * Project: bookapi
 * Last Modified: 2018/09/24 4:09 PM
 */

package services.books

import domain.books.Subsection
import services.CrudService
import services.books.impl.SubsectionServiceImpl

trait SubsectionService extends CrudService[Subsection] {

}

object SubsectionService {
  def apply: SubsectionService = new SubsectionServiceImpl()
}
