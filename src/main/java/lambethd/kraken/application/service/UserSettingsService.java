package lambethd.kraken.application.service;

import domain.UserSettings;
import lambethd.kraken.application.interfaces.IUserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSettingsService implements IUserSettingsService {
    @Autowired
    private IUserSettingsRepository userSettingsRepository;

    @Override
    public UserSettings saveUserSettings(UserSettings userSettings) {
        return userSettingsRepository.save(userSettings);
    }

    @Override
    public UserSettings loadUserSettings(String username) {
        return userSettingsRepository.getUserSettingsByUsername(username);
    }
}
