package lambethd.kraken.application.interfaces;

import domain.UserSettings;

public interface IUserSettingsService {
    UserSettings saveUserSettings(UserSettings userSettings);
    UserSettings loadUserSettings(String username);
}
