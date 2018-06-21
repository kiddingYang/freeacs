package com.github.freeacs.springshell;

import com.github.freeacs.dbi.Identity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class ShellContext {

    private final String dbHost;
    private final String user;

    private String unitType;
    private String profile;
    private String unit;

    @Autowired
    public ShellContext(Identity identity, @Value("${main.datasource.jdbcUrl}") String jdbcUrl) {
        this.user = identity.getUser().getUsername();
        String cleanedUrl = cleanUrl(jdbcUrl);
        URI uri = URI.create(cleanedUrl);
        this.dbHost = uri.getHost();
    }

    void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    void setProfile(String profile) {
        this.profile = profile;
    }

    void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUser() {
        return user;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("shell(").append(user).append("@").append(dbHost).append("):");
        if (unitType != null) {
            sb.append("(").append(unitType).append(":ut):");
        }
        if (profile != null) {
            sb.append("(").append(profile).append(":pr):");
        }
        if (unit != null) {
            sb.append("(").append(unit).append(":u):");
        }
        return sb.toString();
    }

    private String cleanUrl(String jdbcUrl) {
        return (jdbcUrl.contains("?")
                ? jdbcUrl.substring(0, jdbcUrl.indexOf("?"))
                : jdbcUrl).substring(5);
    }
}
