package com.ford.fbms.foe.uscwers.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

/**
 * A class for re-routing default path to swagger path.
 *
 * @author SNITHY11 on 2/7/2021.
 */
@RestController
@Api(value = "Swagger", hidden = true)
public class SwaggerRouter {

  /**
   * To redirect to swagger page when no endpoint is given.
   *
   * @return Swagger page
   */
  @GetMapping(path = "/")
  @ApiIgnore
  @ApiOperation(value = "Swagger Documentation",
      notes = "Redirects default uri to swagger documentation", hidden = true)
  public ModelAndView getApiInfo() {
    return new ModelAndView("redirect:/swagger-ui.html");
  }
}
