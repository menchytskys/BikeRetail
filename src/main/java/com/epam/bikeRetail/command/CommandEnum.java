package com.epam.bikeRetail.command;

import com.epam.bikeRetail.command.admin.*;
import com.epam.bikeRetail.command.common.ChangeLanguageCommand;
import com.epam.bikeRetail.command.common.LoginCommand;
import com.epam.bikeRetail.command.common.LogoutCommand;
import com.epam.bikeRetail.command.user.ReturnBikeCommand;
import com.epam.bikeRetail.command.user.ShowUserStationCommand;
import com.epam.bikeRetail.command.user.TakeBikeCommand;

/**
 * Types of commands.
 *
 * @author Stepan Menchytsky
 * @see ActionCommand
 */
public enum CommandEnum {

    /**
     * Common commands.
     */
    COMMON_LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    COMMON_LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    COMMON_CHANGE_LANGUAGE {
        {
            this.command = new ChangeLanguageCommand();
        }
    },

    /**
     * User commands.
     */
    USER_TAKE_BIKE {
        {
            this.command = new TakeBikeCommand();
        }
    },
    USER_RETURN_BIKE {
        {
            this.command = new ReturnBikeCommand();
        }
    },
    USER_SHOW_USER_STATION {
        {
            this.command = new ShowUserStationCommand();
        }
    },

    /**
     * Admin commands.
     */
    ADMIN_DELETE_BIKE {
        {
            this.command = new DeleteBikeCommand();
        }
    },
    ADMIN_MOVE_BIKE_TO_ANOTHER_STATION {
        {
            this.command = new MoveBikeToAnotherStationCommand();
        }
    },
    ADMIN_SHOW_ALL_USERS {
        {
            this.command = new ShowAllUsersCommand();
        }
    },
    ADMIN_SHOW_ADMIN_STATION {
        {
            this.command = new ShowAdminStationCommand();
        }
    },
    ADMIN_SHOW_ALL_STATIONS {
        {
            this.command = new ShowAllStationsCommand();
        }
    },
    ADMIN_SHOW_ALL_BIKES {
        {
            this.command = new ShowAllBikesCommand();
        }
    },
    ADMIN_ADD_BIKE {
        {
            this.command = new AddBikeCommand();
        }
    },
    ADMIN_CHANGE_USER_STATUS {
        {
            this.command = new ChangeUserStatusCommand();
        }
    };
    ActionCommand command;

    /**
     * Gets current command.
     *
     * @return the current command.
     */
    public ActionCommand getCurrentCommand() {

        return command;
    }

}
