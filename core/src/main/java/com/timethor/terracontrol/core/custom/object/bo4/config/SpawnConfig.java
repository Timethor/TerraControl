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

import com.timethor.terracontrol.core.custom.object.bo4.markers.LightMarker;
import com.timethor.terracontrol.core.custom.object.bo4.markers.BlockMarker;

/**
 * @author Timethor
 */
public class SpawnConfig {

    /**
     * @author Timethor
     */
    public enum Spawnables {

        /**
         */
        Tree,
        /**
         */
        Structure,
        /**
         */
        Object,
        /**
         */
        Sapling
    }
    /**
     *
     */
    public int frequency;
    /**
     *
     */
    public double chance;
    /**
     *
     */
    public Spawnables canSpawnAs;
    /**
     *
     */
    public SpawnRotationConfig src;
    /**
     *
     */
    public SpawnVerticalConfig svc;
    /**
     *
     */
    public SpawnVerticalConfig spc;

    /**
     *
     */
    public static class SpawnRotationConfig {

        /**
         *
         */
        public enum Orientation {

            /**
             *
             */
            Random,
            /**
             *
             */
            Static
        }

        /**
         *
         */
        public enum Cardinal {

            /**
             *
             */
            North,
            /**
             *
             */
            South,
            /**
             *
             */
            East,
            /**
             *
             */
            West
        }
        /**
         *
         */
        public Orientation oriented;
        /**
         *
         */
        public Cardinal[] direction;
    }

    /**
     *
     */
    public static class SpawnVerticalConfig {

        /**
         *
         */
        public enum Mode {

            /**
             *
             */
            Random,
            /**
             *
             */
            Highest,
            /**
             *
             */
            Lowest,
            /**
             *
             */
            Central
        }
        /**
         *
         */
        public boolean solid;
        /**
         *
         */
        public int min;
        /**
         *
         */
        public int average;
        /**
         *
         */
        public int max;
        /**
         *
         */
        public Mode locationMode;
    }

    /**
     *
     */
    public static class SpawnPreventionConfig {

        /**
         *
         */
        public BlockMarker[] VoxelBlockCheck;
        /**
         *
         */
        public LightMarker[] VoxelLightCheck;
        /**
         *
         */
        public BlockMarker[] objectBlockCheck;
        /**
         *
         */
        public LightMarker[] objectLightCheck;
    }
}
