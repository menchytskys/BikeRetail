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

    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    SHOW_USER_STATION {
        {
            this.command = new ShowUserStationCommand();
        }
    },
    SHOW_ADMIN_STATION {
        {
            this.command = new ShowAdminStationCommand();
        }
    },
    SHOW_ALL_STATIONS {
        {
            this.command = new ShowAllStationsCommand();
        }
    },
    SHOW_ALL_BIKES {
        {
            this.command = new ShowAllBikesCommand();
        }
    },
    ADD_BIKE {
        {
            this.command = new AddBikeCommand();
        }
    },
    TAKE_BIKE {
        {
            this.command = new TakeBikeCommand();
        }
    },
    RETURN_BIKE {
        {
            this.command = new ReturnBikeCommand();
        }
    },
    DELETE_BIKE {
        {
            this.command = new DeleteBikeCommand();
        }
    },
    MOVE_BIKE_TO_ANOTHER_STATION {
        {
            this.command = new MoveBikeToAnotherStationCommand();
        }
    },
    SHOW_ALL_USERS {
        {
            this.command = new ShowAllUsersCommand();
        }
    },
    CHANGE_LANGUAGE {
        {
            this.command = new ChangeLanguageCommand();
        }
    },
    CHANGE_USER_STATUS {
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
