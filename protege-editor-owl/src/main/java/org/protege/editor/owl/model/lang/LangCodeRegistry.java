package org.protege.editor.owl.model.lang;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.osgi.framework.Bundle;
import org.protege.editor.core.plugin.PluginUtilities;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-03-15
 */
public class LangCodeRegistry {

    private ImmutableMap<String, LangCode> registry;

    private ImmutableList<LangCode> langCodes;

    private LangCodeRegistry(@Nonnull ImmutableList<LangCode> langCodes,
                             @Nonnull ImmutableMap<String, LangCode> registry) {
        this.langCodes = checkNotNull(langCodes);
        this.registry = checkNotNull(registry);
    }

    public Optional<LangCode> getLangCode(@Nonnull String langCode) {
        return Optional.ofNullable(registry.get(langCode));
    }

    public static LangCodeRegistry get() {
        ImmutableList<LangCode> langCodes = LangCodesResource.getLangCodes();
        ImmutableMap.Builder<String, LangCode> registryBuilder = ImmutableMap.builder();
        langCodes.forEach(lc -> registryBuilder.put(lc.getLangCode().toLowerCase(), lc));
        return new LangCodeRegistry(langCodes, registryBuilder.build());
    }

    public ImmutableList<LangCode> getLangCodes() {
        return langCodes;
    }


}
