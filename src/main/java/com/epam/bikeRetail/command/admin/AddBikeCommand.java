package com.epam.bikeRetail.command.admin;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.entity.Bike;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.resource.MessageManager;
import com.epam.bikeRetail.service.BikeService;
import com.epam.bikeRetail.utils.BikeDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Command to add bike.
 *
 * @author Stepan Menchytsky
 * @see ActionCommand
 * @see HttpServletRequest
 */
public class AddBikeCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AddBikeCommand.
                                                              class.getName());
    private static final String ADD_BIKE_PAGE = "path.page.addBike";
    private static final String ERROR_PAGE = "path.page.error";
    private static final String INVALID_DATA_MESSAGE_PATH = "message.notValid";
    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private static final String STATION_ID = "station";
    private static final String PRICE_ON_HOUR = "priceOnHour";
    private static final String RESULT_ATTRIBUTE = "result";

    /**
     * Implementation of command for add bike.
     *
     * @param request HttpServletRequest object.
     * @return redirect page.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        try {
            String brand = request.getParameter(BRAND);
            String model = request.getParameter(MODEL);
            String bikePriceOnHour = request.getParameter(PRICE_ON_HOUR);
            String bikeStationId = request.getParameter(STATION_ID);

            BikeDataValidator bikeDataValidator = new BikeDataValidator();
            boolean isBikeDataValid = bikeDataValidator.
                                      checkData(brand, model, bikePriceOnHour);

            if (isBikeDataValid) {
                Bike bike = new Bike();
                BigDecimal priceOnHour = new BigDecimal(bikePriceOnHour);
                bike.setBrand(brand);
                bike.setModel(model);
                bike.setPriceOnHour(priceOnHour);

                BikeService bikeService = new BikeService();
                bikeService.addBike(bike, bikeStationId);

                page = ConfigurationManager.getProperty(ADD_BIKE_PAGE);
            } else {
                String property = MessageManager.
                                  getProperty(INVALID_DATA_MESSAGE_PATH);
                request.setAttribute(RESULT_ATTRIBUTE, property);
            }
        } catch (ServiceException e) {
            LOGGER.error("Service exception detected.", e);
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }

        return page;
    }
}
