package edu.cornell.ncrn.model

import edu.cornell.ncrn.Ced2arApplication
import edu.cornell.ncrn.model.testing.BaseRepositoryIT
import edu.cornell.ncrn.service.{CodebookService, CodebookServiceTesters}
import org.junit.{Ignore, Test}
//import org.junit.gen5.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


/**
  * @author Brandon Elam Barker
  *         on 8/11/2016.
  */

@SpringBootTest(classes = Array(classOf[Ced2arApplication]))
//@DatabaseSetup(BaseRepositoryIT.DATASET)
//@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { BaseRepositoryIT.DATASET })
//@DirtiesContext
class Ced2arRepositoryIT extends BaseRepositoryIT with CodebookServiceTesters  {

  //TODO: refactor to services-core-tests module

  @Autowired val codeBookService: CodebookService = null

  @Ignore("Try to fix generic decode issues and go back to Map")
  @Test
  def getAllHandlesJsonTests: Unit = getAllHandlesJsonTests(codeBookService)

  @Test
  def getCodebookDetailsListJsonTests: Unit = getCodebookDetailsListJsonTests(codeBookService)

  @Test
  def getVariableDetailsListJsonTests: Unit = getVariableDetailsListJsonTests(codeBookService)


}
