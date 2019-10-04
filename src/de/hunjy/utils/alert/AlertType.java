package de.hunjy.utils.alert;

import de.hunjy.utils.PrefixBuilder;

public enum AlertType {

    DANGER(new PrefixBuilder("§4Danger").build()),
    WARNING(new PrefixBuilder("§cWarn").build()),
    INFO(new PrefixBuilder("§3Info").build()),
    SUCCESS(new PrefixBuilder("§aSuccess").build());


    AlertType(String prefix) {
        this.prefix = prefix;
    }

    private final String prefix;

    public String getPrefix() {
        return prefix;
    }
}
