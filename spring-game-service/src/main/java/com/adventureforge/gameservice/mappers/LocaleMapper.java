package com.adventureforge.gameservice.mappers;

import org.apache.commons.lang3.LocaleUtils;
import org.mapstruct.Mapper;

import java.util.Locale;

@Mapper(componentModel = "spring")
public interface LocaleMapper {

    default Locale stringToLocale(String localeStr) {
        return LocaleUtils.toLocale(localeStr);
    }

    default String localeToString(Locale locale) {
        return locale.toLanguageTag();
    }
}
