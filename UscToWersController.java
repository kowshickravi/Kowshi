package com.ford.fbms.foe.uscwers.controller;

import com.ford.fbms.foe.uscwers.domain.AddressRequest;
import com.ford.fbms.foe.uscwers.service.AddressLookupService;
import com.ford.fbms.foe.uscwers.service.UscToWersService;
import com.ford.fbms.foe.uscwers.transport.ApiParams;
import com.ford.fbms.foe.uscwers.transport.GenericResponse;
import com.ford.fbms.foe.uscwers.transport.GenericResponseWrapper;
import com.ford.fbms.foe.uscwers.util.ApiResponseDetails;
import com.ford.fbms.foe.uscwers.util.LogAround;
import com.ford.fbms.foe.uscwers.util.RequestMode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * A main controller class to receive requests.
 *
 * @author GILNAGOV on 2/15/2021.
 */
@Slf4j
@RestController
@RequestMapping(path = "/usctowers")
@Api("To initiate web service development for FBMS")
@Validated
public class UscToWersController {

  @Autowired
  private AddressLookupService addressLookupService;

    @Autowired
    private UscToWersService uscToWersService;


    @PreAuthorize("hasPermission('aud', 'createResource')")
    @ApiResponseDetails
    @ApiOperation(value = "To convert USC into WERS vehicle line", notes = "This is one time activity for each vehicle line")
    @PostMapping(value = "/v1/initiate", produces = MediaType.APPLICATION_JSON_VALUE)
    @LogAround
    public GenericResponseWrapper initiateUscToWers(
            @ApiParam(value = "Requester ID.", required = true)
            @RequestParam @Pattern(regexp = "^[A-Za-z0-9]{1,30}$", message = "Invalid Requester ID")
            @Size(min = 1, max = 30, message = "Invalid length of Requester ID")
            @NotBlank final String cdsId,
            @ApiParam(value = "Country Code.", required = true)
            @RequestParam @Pattern(regexp = "^[A-Za-z]{3}$", message = "Invalid Country Code")
            @Size(min = 3, max = 3, message = "Invalid length of Country Code")
            @NotBlank final String countryCd,
            final HttpServletRequest httpRequest, final HttpServletResponse response) throws ParseException {

        //Populate preliminary fields
        final ApiParams apiParams = uscToWersService.buildApiParams(cdsId, countryCd);

        final GenericResponseWrapper genericResponseWrapper = uscToWersService
                .initiateUscToWers(apiParams, RequestMode.USC_TO_WERS_INITIATE, httpRequest);
        response.setStatus(genericResponseWrapper.getGenericResponse().getHttpStatus().value());
        return genericResponseWrapper;
    }
}
