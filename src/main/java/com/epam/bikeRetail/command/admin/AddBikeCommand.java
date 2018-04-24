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

public class AddBikeCommand implements ActionCommand {
    private final Logger LOGGER = LogManager.getLogger(AddBikeCommand.class.getName());
    private final static String ADD_BIKE_PAGE = "path.page.addBike";
    private final static String ERROR_PAGE = "path.page.error";

    private final static String BRAND = "brand";
    private final static String MODEL = "model";
    private final static String STATION_ID = "station";
    private final static String PRICE_ON_HOUR = "priceOnHour";
    private final static String RESULT_ATTRIBUTE = "result";
    private final static String REGISTRATION_FAILED_MESSAGE_PATH = "message.addBikeError";
    private final static String DATA_NOT_VALID_MESSAGE_PATH = "message.notValid";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        try {
            String brand = request.getParameter(BRAND);
            String model = request.getParameter(MODEL);
            String bikePriceOnHour = request.getParameter(PRICE_ON_HOUR);
            String bikeStationId = request.getParameter(STATION_ID);

            BikeDataValidator bikeDataValidator = new BikeDataValidator();
            boolean isBikeDataValid = bikeDataValidator.checkData(brand, model, bikePriceOnHour);

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
                request.setAttribute(RESULT_ATTRIBUTE, MessageManager.getProperty(DATA_NOT_VALID_MESSAGE_PATH));
            }
        } catch (ServiceException e) {
            LOGGER.error("Service exception detected.", e);
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }

        return page;
    }
}
