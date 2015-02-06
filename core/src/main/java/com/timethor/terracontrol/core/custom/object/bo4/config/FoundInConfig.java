/*
 * Copyright (C) 2013 Timethor
 *
 * This program is NOT free software; you can NOT redistribute it and/or
 * modify it without explicit permission from the owner of the codebase.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */
package com.timethor.terracontrol.core.custom.object.bo4.config;

import java.io.File;

/**
 * @author Timethor
 */
public class FoundInConfig {

    /**
     *
     */
    public static class FoundInFiles {

        /**
         *
         */
        public boolean include;
        /**
         *
         */
        public File[] files;
    }

    /**
     *
     */
    public static class FoundInNamespace {

        /**
         *
         */
        public boolean include;
        /**
         *
         */
        public String[] namespaces;
    }
    /**
     *
     */
    public FoundInFiles fileContext;
    /**
     *
     */
    public FoundInNamespace namespaceContext;
}
