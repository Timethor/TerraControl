package com.timethor.terracontrol.core.configuration;

import com.timethor.terracontrol.core.builtin.TerraCatalog;
import com.timethor.terracontrol.core.builtin.BiomeCatalog;
import com.timethor.terracontrol.core.TerraBiome;
import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.biome.generator.BiomeGenerator;
import com.timethor.terracontrol.core.builtin.TerrainControlCatalog;
import com.timethor.terracontrol.core.custom.object.CustomObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

/**
 * @author Timethor
 */
public final class WorldConfig extends ConfigFile {

    /**
     */
    public ArrayList<String> CustomBiomes = new ArrayList<>();
    /**
     */
    public HashMap<String, Integer> CustomBiomeIds = new HashMap<>();
    // Holds all world CustomObjects.
    /**
     */
    public List<CustomObject> customObjects = new ArrayList<>();
    /**
     */
    public ArrayList<String> NormalBiomes = new ArrayList<>();
    /**
     */
    public ArrayList<String> IceBiomes = new ArrayList<>();
    /**
     */
    public ArrayList<String> IsleBiomes = new ArrayList<>();
    /**
     */
    public ArrayList<String> BorderBiomes = new ArrayList<>();
    /**
     */
    public BiomeConfig[] biomeConfigs;  // Must be simple array for fast access. Beware! Some ids may contain null values;
    /**
     */
    public int biomesCount; // Overall biome count in this world.
    /**
     */
    public byte[] ReplaceMatrixBiomes = new byte[256];
    /**
     */
    public boolean HaveBiomeReplace = false;
    // For old biome generator
    /**
     */
    public double oldBiomeSize;
    /**
     */
    public float minMoisture;
    /**
     */
    public float maxMoisture;
    /**
     */
    public float minTemperature;
    /**
     */
    public float maxTemperature;
    // Biome generator
    /**
     */
    public int GenerationDepth;
    /**
     */
    public int BiomeRarityScale;
    /**
     */
    public int LandRarity;
    /**
     */
    public int LandSize;
    /**
     */
    public int LandFuzzy;
    /**
     */
    public int IceRarity;
    /**
     */
    public int IceSize;
    /**
     */
    public int RiverRarity;
    /**
     */
    public int RiverSize;
    /**
     */
    public boolean RiversEnabled;
    /**
     */
    public boolean FrozenOcean;
    // Biome image
    /**
     */
    public String imageFile;
    /**
     */
    public ImageMode imageMode;
    // public int imageZoom;
    /**
     */
    public String imageFillBiome;
    /**
     */
    public int imageXOffset;
    /**
     */
    public int imageZOffset;
    /**
     */
    public HashMap<Integer, Integer> biomeColorMap;
    // Look settings
    /**
     */
    public int WorldFog;
    /**
     */
    public float WorldFogR;
    /**
     */
    public float WorldFogG;
    /**
     */
    public float WorldFogB;
    /**
     */
    public int WorldNightFog;
    /**
     */
    public float WorldNightFogR;
    /**
     */
    public float WorldNightFogG;
    /**
     */
    public float WorldNightFogB;
    // Specific biome settings
    // Caves
    /**
     */
    public int caveRarity;
    /**
     */
    public int caveFrequency;
    /**
     */
    public int caveMinAltitude;
    /**
     */
    public int caveMaxAltitude;
    /**
     */
    public int individualCaveRarity;
    /**
     */
    public int caveSystemFrequency;
    /**
     */
    public int caveSystemPocketChance;
    /**
     */
    public int caveSystemPocketMinSize;
    /**
     */
    public int caveSystemPocketMaxSize;
    /**
     */
    public boolean evenCaveDistribution;
    // Canyons
    /**
     */
    public int canyonRarity;
    /**
     */
    public int canyonMinAltitude;
    /**
     */
    public int canyonMaxAltitude;
    /**
     */
    public int canyonMinLength;
    /**
     */
    public int canyonMaxLength;
    /**
     */
    public double canyonDepth;
    // Strongholds
    /**
     */
    public boolean strongholdsEnabled;
    /**
     */
    public double strongholdDistance;
    /**
     */
    public int strongholdCount;
    /**
     */
    public int strongholdSpread;
    // Villages
    /**
     */
    public boolean villagesEnabled;
    /**
     */
    public int villageSize;
    /**
     */
    public int villageDistance; // Has a minimum of 9
    // Pyramids (also swamp huts and jungle temples)
    /**
     */
    public boolean rareBuildingsEnabled;
    /**
     */
    public int minimumDistanceBetweenRareBuildings; // Minecraft's internal
    // value is 1 chunk lower
    /**
     */
    public int maximumDistanceBetweenRareBuildings;
    // Other structures
    /**
     */
    public boolean mineshaftsEnabled;
    /**
     */
    public boolean netherFortressesEnabled;
    // Terrain
    /**
     */
    public boolean oldTerrainGenerator;
    /**
     */
    public int waterLevelMax;
    /**
     */
    public int waterLevelMin;
    /**
     */
    public int waterBlock;
    /**
     */
    public int iceBlock;
    /**
     */
    public double fractureHorizontal;
    /**
     */
    public double fractureVertical;
    /**
     */
    public boolean disableBedrock;
    /**
     */
    public boolean flatBedrock;
    /**
     */
    public boolean ceilingBedrock;
    /**
     */
    public int bedrockBlock;
    /**
     */
    public boolean removeSurfaceStone;
    /**
     */
    public int objectSpawnRatio;
    /**
     */
    public File customObjectsDirectory;
    /**
     */
    public ConfigMode SettingsMode;
    /**
     */
    public boolean isDeprecated = false;
    /**
     */
    public WorldConfig newSettings = null;
    /**
     */
    public TerrainMode ModeTerrain;
    /**
     */
    public Class<? extends BiomeGenerator> biomeMode;
    /**
     */
    public boolean BiomeConfigsHaveReplacement = false;
    /**
     */
    public int normalBiomesRarity;
    /**
     */
    public int iceBiomesRarity;
    /**
     */
    public int worldHeightBits;
    /**
     */
    public int WorldHeight;
    /**
     */
    public long resourcesSeed;

    /**
     *
     * @param settingsDir
     * @param world
     * @param checkOnly
     */
    public WorldConfig(File settingsDir, TerraWorld world, boolean checkOnly) {
        this.file = settingsDir;
        this.name = world.getName();
        File settingsFile = null;
        File newSettingsFile = null;
        for (String listedFile : this.file.list()) {
            if (listedFile.endsWith(TerrainControlCatalog.WorldSettingsName.stringValue())) {
                settingsFile = new File(this.file, TerrainControlCatalog.WorldSettingsName.stringValue());
                break;
            }
        }

        for (String listedFile : this.file.list()) {
            if (listedFile.endsWith(TerraCatalog.WorldConfigExtension.stringValue())) {
                newSettingsFile = new File(this.file, TerrainControlCatalog.WorldSettingsName.stringValue());
                break;
            }
        }


        if (settingsFile != null) {
            this.readSettingsFile(settingsFile);
        } else {
            this.logFileNotFound(settingsFile);
        }
        if (newSettingsFile != null) {
            this.readSettingsFile(newSettingsFile);
        } else {
            TerraControl.log(Level.ALL, "No World Config with new format");
        }

        this.renameOldSettings();
        this.readConfigSettings();

        this.correctSettings();

        ReadWorldCustomObjects();

        // Check biome ids

        for (String biomeName : CustomBiomes) {
            if (CustomBiomeIds.get(biomeName) == -1) {
                CustomBiomeIds.put(biomeName, world.getFreeBiomeId());
            }
        }

        // Need add check to clashes
        if (this.SettingsMode != ConfigMode.WriteDisable) {
            this.writeSettingsFile(settingsFile, (this.SettingsMode == ConfigMode.WriteAll));
        }

        world.setHeightBits(this.worldHeightBits);

        File BiomeFolder = new File(file, TerrainControlCatalog.WorldBiomeConfigDirectoryName.stringValue());
        if (!BiomeFolder.exists()) {
            if (!BiomeFolder.mkdir()) {
                TerraControl.log(Level.WARNING, "Error creating biome configs directory, working with defaults");
                return;
            }
        }

        ArrayList<TerraBiome> localBiomes = new ArrayList<>(world.getDefaultBiomes());

        // Add custom biomes to world
        for (String biomeName : this.CustomBiomes) {
            if (checkOnly) {
                localBiomes.add(world.getNullBiome(biomeName));
            } else {
                localBiomes.add(world.AddBiome(biomeName, this.CustomBiomeIds.get(biomeName)));
            }
        }

        // Build biome replace matrix
        for (int i = 0; i < this.ReplaceMatrixBiomes.length; i++) {
            this.ReplaceMatrixBiomes[i] = (byte) i;
        }

        this.biomeConfigs = new BiomeConfig[world.getMaxBiomesCount()];
        this.biomesCount = 0;

        String LoadedBiomeNames = "";

        for (TerraBiome localBiome : localBiomes) {
            BiomeConfig config = new BiomeConfig(BiomeFolder, localBiome, this);
            if (checkOnly) {
                continue;
            }

            if (!config.ReplaceBiomeName.equals("")) {
                this.HaveBiomeReplace = true;
                this.ReplaceMatrixBiomes[config.Biome.getId()] = (byte) world.getBiomeIdByName(config.ReplaceBiomeName);
            }

            if (this.NormalBiomes.contains(config.name)) {
                this.normalBiomesRarity += config.BiomeRarity;
            }
            if (this.IceBiomes.contains(config.name)) {
                this.iceBiomesRarity += config.BiomeRarity;
            }

            if (!this.BiomeConfigsHaveReplacement) {
                this.BiomeConfigsHaveReplacement = config.ReplaceCount > 0;
            }
            if (biomesCount != 0) {
                LoadedBiomeNames += ", ";
            }
            LoadedBiomeNames += localBiome.getName();
            // Add biome to the biome array
            if (this.biomeConfigs[localBiome.getId()] == null) {
                // Only if it won't overwrite another biome in the array
                biomesCount++;
            } else {
                TerraControl.log(Level.WARNING, "Duplicate biome id " + localBiome.getId() + " (" + this.biomeConfigs[localBiome.getId()].name + " and " + config.name + ")!");
            }
            this.biomeConfigs[localBiome.getId()] = config;

            if (this.biomeMode == TerraControl.getBiomeModeManager().FROM_IMAGE) {
                if (this.biomeColorMap == null) {
                    this.biomeColorMap = new HashMap<>();
                }

                try {
                    int color = Integer.decode(config.BiomeColor);
                    if (color <= 0xFFFFFF) {
                        this.biomeColorMap.put(color, config.Biome.getId());
                    }
                } catch (NumberFormatException ex) {
                    TerraControl.log(Level.WARNING, "Wrong color in " + config.Biome.getName());
                }

            }
        }

        TerraControl.log(Level.INFO, "Loaded " + biomesCount + " biomes");
        TerraControl.log(Level.FINER, "Loaded biomes: " + LoadedBiomeNames);

    }

    private void ReadWorldCustomObjects() {
        customObjectsDirectory = new File(this.file, TerrainControlCatalog.BO_WorldDirectoryName.stringValue());

        File oldCustomObjectsDirectory = new File(file, "BOBPlugins");
        if (oldCustomObjectsDirectory.exists()) {
            if (!oldCustomObjectsDirectory.renameTo(new File(file, TerrainControlCatalog.BO_WorldDirectoryName.stringValue()))) {
                TerraControl.log(Level.WARNING, "Fould old BOBPlugins folder, but it cannot be renamed to WorldObjects.");
                TerraControl.log(Level.WARNING, "Please move the BO2s manually and delete BOBPlugins afterwards.");
            }
        }

        if (!customObjectsDirectory.exists()) {
            if (!customObjectsDirectory.mkdirs()) {
                TerraControl.log(Level.WARNING, "Can`t create WorldObjects folder. No write permissions?");
                return;
            }
        }

        customObjects = new ArrayList<>(TerraControl.getCustomObjectManager().loadObjects(customObjectsDirectory).values());

        TerraControl.log(Level.INFO, customObjects.size() + " world custom objects loaded.");

    }

    /**
     *
     */
    @Override
    protected void renameOldSettings() {
        renameOldSetting("WaterLevel", TerraCatalog.WaterLevelMax);
        renameOldSetting("ModeTerrain", TerraCatalog.TerrainMode);
        renameOldSetting("ModeBiome", TerraCatalog.BiomeMode);
        renameOldSetting("NetherFortressEnabled", TerraCatalog.NetherFortressesEnabled);
        renameOldSetting("PyramidsEnabled", TerraCatalog.RareBuildingsEnabled);
    }

    /**
     *
     */
    @Override
    protected void correctSettings() {
        this.oldBiomeSize = applyBounds(this.oldBiomeSize, 0.1D, 10.0D);

        this.GenerationDepth = applyBounds(this.GenerationDepth, 1, 20);
        this.BiomeRarityScale = applyBounds(this.BiomeRarityScale, 1, Integer.MAX_VALUE);

        this.LandRarity = applyBounds(this.LandRarity, 1, 100);
        this.LandSize = applyBounds(this.LandSize, 0, this.GenerationDepth);
        this.LandFuzzy = applyBounds(this.LandFuzzy, 0, this.GenerationDepth - this.LandSize);

        this.IceRarity = applyBounds(this.IceRarity, 1, 100);
        this.IceSize = applyBounds(this.IceSize, 0, this.GenerationDepth);

        this.RiverRarity = applyBounds(this.RiverRarity, 0, this.GenerationDepth);
        this.RiverSize = applyBounds(this.RiverSize, 0, this.GenerationDepth - this.RiverRarity);

        this.NormalBiomes = filterBiomes(this.NormalBiomes, this.CustomBiomes);
        this.IceBiomes = filterBiomes(this.IceBiomes, this.CustomBiomes);
        this.IsleBiomes = filterBiomes(this.IsleBiomes, this.CustomBiomes);
        this.BorderBiomes = filterBiomes(this.BorderBiomes, this.CustomBiomes);

        if (this.biomeMode == TerraControl.getBiomeModeManager().FROM_IMAGE) {
            File mapFile = new File(file, imageFile);
            if (!mapFile.exists()) {
                TerraControl.log(Level.INFO, "Biome map file not found. Switching BiomeMode to Normal");
                this.biomeMode = TerraControl.getBiomeModeManager().NORMAL;
            }
        }

        this.imageFillBiome = (BiomeCatalog.Contain(imageFillBiome) || CustomBiomes.contains(imageFillBiome)) ? imageFillBiome : TerraCatalog.ImageFillBiome.stringValue();

        this.minMoisture = applyBounds(this.minMoisture, 0, 1.0F);
        this.maxMoisture = applyBounds(this.maxMoisture, 0, 1.0F, this.minMoisture);

        this.minTemperature = applyBounds(this.minTemperature, 0, 1.0F);
        this.maxTemperature = applyBounds(this.maxTemperature, 0, 1.0F, this.minTemperature);

        this.caveRarity = applyBounds(this.caveRarity, 0, 100);
        this.caveFrequency = applyBounds(this.caveFrequency, 0, 200);
        this.caveMinAltitude = applyBounds(this.caveMinAltitude, 0, WorldHeight);
        this.caveMaxAltitude = applyBounds(this.caveMaxAltitude, 0, WorldHeight, this.caveMinAltitude);
        this.individualCaveRarity = applyBounds(this.individualCaveRarity, 0, 100);
        this.caveSystemFrequency = applyBounds(this.caveSystemFrequency, 0, 200);
        this.caveSystemPocketChance = applyBounds(this.caveSystemPocketChance, 0, 100);
        this.caveSystemPocketMinSize = applyBounds(this.caveSystemPocketMinSize, 0, 100);
        this.caveSystemPocketMaxSize = applyBounds(this.caveSystemPocketMaxSize, 0, 100, this.caveSystemPocketMinSize);

        this.canyonRarity = applyBounds(this.canyonRarity, 0, 100);
        this.canyonMinAltitude = applyBounds(this.canyonMinAltitude, 0, WorldHeight);
        this.canyonMaxAltitude = applyBounds(this.canyonMaxAltitude, 0, WorldHeight, this.canyonMinAltitude);
        this.canyonMinLength = applyBounds(this.canyonMinLength, 1, 500);
        this.canyonMaxLength = applyBounds(this.canyonMaxLength, 1, 500, this.canyonMinLength);
        this.canyonDepth = applyBounds(this.canyonDepth, 0.1D, 15D);

        this.waterLevelMin = applyBounds(this.waterLevelMin, 0, WorldHeight - 1);
        this.waterLevelMax = applyBounds(this.waterLevelMax, 0, WorldHeight - 1, this.waterLevelMin);

        this.villageDistance = applyBounds(this.villageDistance, 9, Integer.MAX_VALUE);
        this.minimumDistanceBetweenRareBuildings = applyBounds(this.minimumDistanceBetweenRareBuildings, 1, Integer.MAX_VALUE);
        this.maximumDistanceBetweenRareBuildings = applyBounds(this.maximumDistanceBetweenRareBuildings, this.minimumDistanceBetweenRareBuildings, Integer.MAX_VALUE);

        if (this.biomeMode == TerraControl.getBiomeModeManager().OLD_GENERATOR && this.ModeTerrain != TerrainMode.OldGenerator) {
            TerraControl.log(Level.INFO, "Old biome generator works only with old terrain generator!");
            this.biomeMode = TerraControl.getBiomeModeManager().NORMAL;

        }
    }

    /**
     *
     */
    @Override
    protected void readConfigSettings() {
        // Main modes
        this.SettingsMode = readSettings(TerrainControlCatalog.SettingsMode);
        this.ModeTerrain = readSettings(TerraCatalog.TerrainMode);
        this.biomeMode = TerraControl.getBiomeModeManager().getBiomeManager((String) readSettings(TerraCatalog.BiomeMode));

        // World and water height
        this.worldHeightBits = readSettings(TerraCatalog.WorldHeightBits);
        this.worldHeightBits = applyBounds(this.worldHeightBits, 5, 8);
        this.WorldHeight = 1 << worldHeightBits;
        this.waterLevelMax = WorldHeight / 2 - 1;

        // Biome placement
        this.GenerationDepth = readSettings(TerraCatalog.GenerationDepth);

        this.BiomeRarityScale = readSettings(TerraCatalog.BiomeRarityScale);
        this.LandRarity = readSettings(TerraCatalog.LandRarity);
        this.LandSize = readSettings(TerraCatalog.LandSize);
        this.LandFuzzy = readSettings(TerraCatalog.LandFuzzy);

        this.IceRarity = readSettings(TerraCatalog.IceRarity);
        this.IceSize = readSettings(TerraCatalog.IceSize);

        this.RiverRarity = readSettings(TerraCatalog.RiverRarity);
        this.RiverSize = readSettings(TerraCatalog.RiverSize);
        this.RiversEnabled = readSettings(TerraCatalog.RiversEnabled);

        this.FrozenOcean = readSettings(TerraCatalog.FrozenOcean);

        // Biomes
        this.NormalBiomes = readSettings(TerraCatalog.NormalBiomes);
        this.IceBiomes = readSettings(TerraCatalog.IceBiomes);
        this.IsleBiomes = readSettings(TerraCatalog.IsleBiomes);
        this.BorderBiomes = readSettings(TerraCatalog.BorderBiomes);
        ReadCustomBiomes();

        // Images
        this.imageMode = readSettings(TerraCatalog.ImageMode);
        this.imageFile = this.readSettings(TerraCatalog.ImageFile);
        this.imageFillBiome = this.readSettings(TerraCatalog.ImageFillBiome);
        this.imageXOffset = this.readSettings(TerraCatalog.ImageXOffset);
        this.imageZOffset = this.readSettings(TerraCatalog.ImageZOffset);

        // Old biomes
        this.oldBiomeSize = readSettings(TerraCatalog.oldBiomeSize);
        this.minMoisture = readSettings(TerraCatalog.minMoisture);
        this.maxMoisture = readSettings(TerraCatalog.maxMoisture);
        this.minTemperature = readSettings(TerraCatalog.minTemperature);
        this.maxTemperature = readSettings(TerraCatalog.maxTemperature);

        // Fog
        this.WorldFog = readSettings(TerraCatalog.WorldFog);
        this.WorldNightFog = readSettings(TerraCatalog.WorldNightFog);

        this.WorldFogR = ((WorldFog & 0xFF0000) >> 16) / 255F;
        this.WorldFogG = ((WorldFog & 0xFF00) >> 8) / 255F;
        this.WorldFogB = (WorldFog & 0xFF) / 255F;

        this.WorldNightFogR = ((WorldNightFog & 0xFF0000) >> 16) / 255F;
        this.WorldNightFogG = ((WorldNightFog & 0xFF00) >> 8) / 255F;
        this.WorldNightFogB = (WorldNightFog & 0xFF) / 255F;

        // Structures
        this.strongholdsEnabled = readSettings(TerraCatalog.StrongholdsEnabled);
        this.strongholdCount = readSettings(TerraCatalog.StrongholdCount);
        this.strongholdDistance = readSettings(TerraCatalog.StrongholdDistance);
        this.strongholdSpread = readSettings(TerraCatalog.StrongholdSpread);

        this.villagesEnabled = readSettings(TerraCatalog.VillagesEnabled);
        this.villageDistance = readSettings(TerraCatalog.VillageDistance);
        this.villageSize = readSettings(TerraCatalog.VillageSize);

        this.rareBuildingsEnabled = readSettings(TerraCatalog.RareBuildingsEnabled);
        this.minimumDistanceBetweenRareBuildings = readSettings(TerraCatalog.MinimumDistanceBetweenRareBuildings);
        this.maximumDistanceBetweenRareBuildings = readSettings(TerraCatalog.MaximumDistanceBetweenRareBuildings);

        this.mineshaftsEnabled = readSettings(TerraCatalog.MineshaftsEnabled);
        this.netherFortressesEnabled = readSettings(TerraCatalog.NetherFortressesEnabled);

        // Caves
        this.caveRarity = readSettings(TerraCatalog.caveRarity);
        this.caveFrequency = readSettings(TerraCatalog.caveFrequency);
        this.caveMinAltitude = readSettings(TerraCatalog.caveMinAltitude);
        this.caveMaxAltitude = readSettings(TerraCatalog.caveMaxAltitude);
        this.individualCaveRarity = readSettings(TerraCatalog.individualCaveRarity);
        this.caveSystemFrequency = readSettings(TerraCatalog.caveSystemFrequency);
        this.caveSystemPocketChance = readSettings(TerraCatalog.caveSystemPocketChance);
        this.caveSystemPocketMinSize = readSettings(TerraCatalog.caveSystemPocketMinSize);
        this.caveSystemPocketMaxSize = readSettings(TerraCatalog.caveSystemPocketMaxSize);
        this.evenCaveDistribution = readSettings(TerraCatalog.evenCaveDistribution);

        // Canyons
        this.canyonRarity = readSettings(TerraCatalog.canyonRarity);
        this.canyonMinAltitude = readSettings(TerraCatalog.canyonMinAltitude);
        this.canyonMaxAltitude = readSettings(TerraCatalog.canyonMaxAltitude);
        this.canyonMinLength = readSettings(TerraCatalog.canyonMinLength);
        this.canyonMaxLength = readSettings(TerraCatalog.canyonMaxLength);
        this.canyonDepth = readSettings(TerraCatalog.canyonDepth);

        // Water
        this.waterLevelMax = readSettings(TerraCatalog.WaterLevelMax);
        this.waterLevelMin = readSettings(TerraCatalog.WaterLevelMin);
        this.waterBlock = readSettings(TerraCatalog.WaterBlock);
        this.iceBlock = readSettings(TerraCatalog.IceBlock);

        // Fracture
        this.fractureHorizontal = readSettings(TerraCatalog.FractureHorizontal);
        this.fractureVertical = readSettings(TerraCatalog.FractureVertical);

        // Bedrock
        this.disableBedrock = readSettings(TerraCatalog.DisableBedrock);
        this.ceilingBedrock = readSettings(TerraCatalog.CeilingBedrock);
        this.flatBedrock = readSettings(TerraCatalog.FlatBedrock);
        this.bedrockBlock = readSettings(TerraCatalog.BedrockobBlock);

        // Misc
        this.removeSurfaceStone = readSettings(TerraCatalog.RemoveSurfaceStone);
        this.objectSpawnRatio = readSettings(TerraCatalog.objectSpawnRatio);
        this.resourcesSeed = readSettings(TerraCatalog.ResourcesSeed);

        this.oldTerrainGenerator = this.ModeTerrain == TerrainMode.OldGenerator;
    }

    private void ReadCustomBiomes() {

        ArrayList<String> biomes = this.readSettings(TerraCatalog.CustomBiomes);

        for (String biome : biomes) {
            try {
                String[] keys = biome.split(":");
                int id = -1;
                if (keys.length == 2) {
                    id = Integer.valueOf(keys[1]);
                }
                CustomBiomes.add(keys[0]);
                CustomBiomeIds.put(keys[0], id);

            } catch (NumberFormatException e) {
                System.out.println("Wrong custom biome id settings: '" + biome + "'");
            }

        }

    }

    /**
     *
     * @throws IOException
     */
    @Override
    protected void writeConfigSettings() throws IOException {
        // The modes
        writeBigTitle("The modes");
        writeComment("What Terrain Control does with the config files.");
        writeComment("Possible modes: WriteAll, WriteWithoutComments, WriteDisable");
        writeComment("   WriteAll - default");
        writeComment("   WriteWithoutComments - write config files without help comments");
        writeComment("   WriteDisable - doesn't write to the config files, it only reads. Doesn't auto-update the configs. Use with care!");
        writeValue(TerrainControlCatalog.SettingsMode.name(), this.SettingsMode.name());
        writeNewLine();

        writeComment("Possible terrain modes: Normal, OldGenerator, TerrainTest, NotGenerate, Default");
        writeComment("   Normal - use all features");
        writeComment("   OldGenerator - generate land like 1.7.3 generator");
        writeComment("   TerrainTest - generate only terrain without any resources");
        writeComment("   NotGenerate - generate empty chunks");
        writeComment("   Default - use default terrain generator");
        writeValue(TerraCatalog.TerrainMode.name(), this.ModeTerrain.name());
        writeNewLine();

        writeComment("Possible biome modes: Normal, OldGenerator, Default");
        writeComment("   Normal - use all features");
        writeComment("   FromImage - get biomes from image file");
        writeComment("   OldGenerator - generate biome like the Beta 1.7.3 generator");
        writeComment("   Default - use default Notch biome generator");
        writeValue(TerraCatalog.BiomeMode.name(), TerraControl.getBiomeModeManager().getName(biomeMode));

        // Custom biomes
        writeBigTitle("Custom biomes");
        writeComment("You need to register your custom biomes here. This setting will make Terrain Control");
        writeComment("generate setting files for them. However, it won't place them in the world automatically.");
        writeComment("See the settings for your BiomeMode below on how to add them to the world.");
        writeComment("");
        writeComment("Syntax: CustomBiomes:BiomeName:id[,AnotherBiomeName:id[,...]]");
        writeComment("Example: CustomBiomes:TestBiome1:30,BiomeTest2:31");
        writeComment("This will add two biomes and generate the BiomeConfigs for them.");
        writeComment("All changes here need a server restart.");
        writeComment("");
        writeComment("Due to the way Mojang's loading code works, all biome ids need to be unique");
        writeComment("on the server. If you don't do this, the client will display the biomes just fine,");
        writeComment("but the server can think it is another biome with the same id. This will cause saplings,");
        writeComment("snowfall and mobs to work as in the other biome.");
        writeComment("");
        writeComment("The available ids range from 0 to 255 and the ids 0 to " + (BiomeCatalog.values().length - 1) + " are occupied by vanilla minecraft");
        writeComment("biomes. To leave room for new vanilla biomes, it is recommend to not use ids below 30.");

        WriteCustomBiomes();

        // Settings for BiomeMode:Normal
        writeBigTitle("Settings for BiomeMode:Normal");
        writeComment("Also applies if you are using BiomeMode:FromImage and ImageMode:ContinueNormal.");
        writeNewLine();

        writeComment("IMPORTANT value for generation. Bigger values appear to zoom out. All 'Sizes' must be smaller than this.");
        writeComment("Large %/total area biomes (Continents) must be set small, (limit=0)");
        writeComment("Small %/total area biomes (Oasis,Mountain Peaks) must be larger (limit=GenerationDepth)");
        writeComment("This could also represent \"Total number of biome sizes\" ");
        writeComment("Small values (about 1-2) and Large values (about 20) may affect generator performance.");
        writeValue(TerraCatalog.GenerationDepth.name(), this.GenerationDepth);
        writeNewLine();

        writeComment("Max biome rarity from 1 to infinity. By default this is 100, but you can raise it for");
        writeComment("fine-grained control, or to create biomes with a chance of occurring smaller than 1/100.");
        writeValue(TerraCatalog.BiomeRarityScale.name(), this.BiomeRarityScale);
        writeNewLine();

        writeSmallTitle("Biome lists");

        writeComment("Don't forget to register your custom biomes first in CustomBiomes!");
        writeNewLine();

        writeComment("Biomes which used in normal biome algorithm. Biome name is case sensitive.");
        writeValue(TerraCatalog.NormalBiomes.name(), this.NormalBiomes);
        writeNewLine();

        writeComment("Biomes which used in ice biome algorithm. Biome name is case sensitive.");
        writeValue(TerraCatalog.IceBiomes.name(), this.IceBiomes);
        writeNewLine();

        writeComment("Biomes which used as isles. You must set IsleInBiome in biome config for each biome here. Biome name is case sensitive.");
        writeValue(TerraCatalog.IsleBiomes.name(), this.IsleBiomes);
        writeNewLine();

        writeComment("Biomes which used as borders. You must set BiomeIsBorder in biome config for each biome here. Biome name is case sensitive.");
        writeValue(TerraCatalog.BorderBiomes.name(), this.BorderBiomes);
        writeNewLine();

        writeSmallTitle("Landmass settings (for NormalBiomes)");

        writeComment("Land rarity from 100 to 1. If you set smaller than 90 and LandSize near 0 beware Big oceans.");
        writeValue(TerraCatalog.LandRarity.name(), this.LandRarity);
        writeNewLine();

        writeComment("Land size from 0 to GenerationDepth.");
        writeValue(TerraCatalog.LandSize.name(), this.LandSize);
        writeNewLine();

        writeComment("Make land more fuzzy and make lakes. Must be from 0 to GenerationDepth - LandSize");
        writeValue(TerraCatalog.LandFuzzy.name(), this.LandFuzzy);
        writeNewLine();

        writeSmallTitle("Ice area settings (for IceBiomes)");

        writeComment("Ice areas rarity from 100 to 1. If you set smaller than 90 and IceSize near 0 beware ice world");
        writeValue(TerraCatalog.IceRarity.name(), this.IceRarity);
        writeNewLine();

        writeComment("Ice area size from 0 to GenerationDepth.");
        writeValue(TerraCatalog.IceSize.name(), this.IceSize);
        writeNewLine();

        writeValue(TerraCatalog.FrozenOcean.name(), this.FrozenOcean);
        writeNewLine();

        writeSmallTitle("River settings");

        writeComment("River rarity.Must be from 0 to GenerationDepth.");
        writeValue(TerraCatalog.RiverRarity.name(), this.RiverRarity);
        writeNewLine();

        writeComment("River size from 0 to GenerationDepth - RiverRarity");
        writeValue(TerraCatalog.RiverSize.name(), this.RiverSize);
        writeNewLine();

        writeValue(TerraCatalog.RiversEnabled.name(), this.RiversEnabled);
        writeNewLine();

        // Settings for BiomeMode:FromImage
        writeBigTitle("Settings for BiomeMode:FromImage");

        writeComment("Possible modes when generator outside image boundaries: Repeat, ContinueNormal, FillEmpty");
        writeComment("   Repeat - repeat image");
        writeComment("   ContinueNormal - continue normal generation");
        writeComment("   FillEmpty - fill by biome in \"ImageFillBiome settings\" ");
        writeValue(TerraCatalog.ImageMode.name(), this.imageMode.name());
        writeNewLine();

        writeComment("Source png file for FromImage biome mode.");
        writeValue(TerraCatalog.ImageFile.name(), this.imageFile);
        writeNewLine();

        writeComment("Biome name for fill outside image boundaries with FillEmpty mode.");
        writeValue(TerraCatalog.ImageFillBiome.name(), this.imageFillBiome);
        writeNewLine();

        writeComment("Shifts map position from x=0 and z=0 coordinates.");
        writeValue(TerraCatalog.ImageXOffset.name(), this.imageXOffset);
        writeValue(TerraCatalog.ImageZOffset.name(), this.imageZOffset);

        // Terrain Generator Variables
        writeBigTitle("Terrain Generator Variables");
        writeComment("Height bits determinate generation height. Min 5, max 8");
        writeComment("For example 7 = 128 height, 8 = 256 height");
        writeValue(TerraCatalog.WorldHeightBits.name(), this.worldHeightBits);
        writeNewLine();

        writeComment("Set water level. Every empty block under this level will be fill water or another block from WaterBlock ");
        writeValue(TerraCatalog.WaterLevelMax.name(), this.waterLevelMax);
        writeValue(TerraCatalog.WaterLevelMin.name(), this.waterLevelMin);
        writeNewLine();
        writeComment("BlockId used as water in WaterLevel");
        writeValue(TerraCatalog.WaterBlock.name(), this.waterBlock);
        writeNewLine();
        writeComment("BlockId used as ice");
        writeValue(TerraCatalog.IceBlock.name(), this.iceBlock);

        writeNewLine();
        writeComment("Can increase (values greater than 0) or decrease (values less than 0) how much the landscape is fractured horizontally.");
        writeValue(TerraCatalog.FractureHorizontal.name(), this.fractureHorizontal);

        writeNewLine();
        writeComment("Can increase (values greater than 0) or decrease (values less than 0) how much the landscape is fractured vertically.");
        writeComment("Positive values will lead to large cliffs/overhangs, floating islands, and/or a cavern world depending on other settings.");
        writeValue(TerraCatalog.FractureVertical.name(), this.fractureVertical);

        writeNewLine();
        writeComment("Attempts to replace all surface stone with biome surface block");
        writeValue(TerraCatalog.RemoveSurfaceStone.name(), this.removeSurfaceStone);

        writeNewLine();
        writeComment("Disable bottom of map bedrock generation");
        writeValue(TerraCatalog.DisableBedrock.name(), this.disableBedrock);

        writeNewLine();
        writeComment("Enable ceiling of map bedrock generation");
        writeValue(TerraCatalog.CeilingBedrock.name(), this.ceilingBedrock);

        writeNewLine();
        writeComment("Make bottom layer of bedrock flat");
        writeValue(TerraCatalog.FlatBedrock.name(), this.flatBedrock);

        writeNewLine();
        writeComment("BlockId used as bedrock");
        writeValue(TerraCatalog.BedrockobBlock.name(), this.bedrockBlock);

        writeNewLine();
        writeComment("Seed used for the resource generation. Can only be numeric. Leave blank to use the world seed.");
        if (this.resourcesSeed == 0) {   // It's zero, so leave it blank, we're using the world seed
            writeValue(TerraCatalog.ResourcesSeed.name(), "");
        } else {
            writeValue(TerraCatalog.ResourcesSeed.name(), this.resourcesSeed);
        }

        if (objectSpawnRatio != 1) {
            // Write the old objectSpawnRatio
            writeNewLine();
            writeComment("LEGACY setting for compability with old worlds. This setting should be kept at 1.");
            writeComment("If the setting is set at 1, the setting will vanish from the config file. Readd it");
            writeComment("manually with another value and it will be back.");
            writeComment("");
            writeComment("When using the UseWorld or UseBiome keyword for spawning custom objects, Terrain Control");
            writeComment("spawns one of the possible custom objects. There is of course a chance that");
            writeComment("the chosen object cannot spawn. This setting tells TC how many times it should");
            writeComment("try to spawn that object.");
            writeComment("This setting doesn't affect growing saplings anymore.");
            this.writeValue(TerraCatalog.objectSpawnRatio.name(), this.objectSpawnRatio);
        }

        // Strongholds
        writeBigTitle("Strongholds");
        writeComment("Not much is known about these settings. They are directly passed to the stronghold generator.");
        writeValue(TerraCatalog.StrongholdsEnabled.name(), this.strongholdsEnabled);
        writeNewLine();
        writeValue(TerraCatalog.StrongholdCount.name(), this.strongholdCount);
        writeNewLine();
        writeValue(TerraCatalog.StrongholdDistance.name(), this.strongholdDistance);
        writeNewLine();
        writeValue(TerraCatalog.StrongholdSpread.name(), this.strongholdSpread);

        // Villages
        writeBigTitle("Villages");
        writeComment("Whether the villages are enabled or not.");
        writeValue(TerraCatalog.VillagesEnabled.name(), this.villagesEnabled);
        writeNewLine();
        writeComment("The size of the village. Larger is bigger. Normal worlds have 0 as default, superflat worlds 1.");
        writeValue(TerraCatalog.VillageSize.name(), this.villageSize);
        writeNewLine();
        writeComment("The minimum distance between the village centers in chunks. Minimum value is 9.");
        writeValue(TerraCatalog.VillageDistance.name(), this.villageDistance);

        // Rare buildings
        writeBigTitle("Rare buildings");
        writeComment("Rare buildings are either desert pyramids, jungle temples or swamp huts.");
        writeNewLine();
        writeComment("Whether rare buildings are enabled.");
        writeValue(TerraCatalog.RareBuildingsEnabled.name(), this.rareBuildingsEnabled);
        writeNewLine();
        writeComment("The minimum distance between rare buildings in chunks.");
        writeValue(TerraCatalog.MinimumDistanceBetweenRareBuildings.name(), this.minimumDistanceBetweenRareBuildings);
        writeNewLine();
        writeComment("The maximum distance between rare buildings in chunks.");
        writeValue(TerraCatalog.MaximumDistanceBetweenRareBuildings.name(), this.maximumDistanceBetweenRareBuildings);

        // Other structures
        writeBigTitle("Other structures");
        writeValue(TerraCatalog.MineshaftsEnabled.name(), this.mineshaftsEnabled);
        writeValue(TerraCatalog.NetherFortressesEnabled.name(), this.netherFortressesEnabled);

        // Visual settings
        this.writeBigTitle("Visual settings");
        this.writeComment("Warning this section will work only for players with the single version of Terrain Control installed.");

        writeComment("World fog color");
        writeColorValue(TerraCatalog.WorldFog.name(), this.WorldFog);
        this.writeNewLine();

        writeComment("World night fog color");
        writeColorValue(TerraCatalog.WorldNightFog.name(), this.WorldNightFog);
        this.writeNewLine();

        // Cave settings (still using code from Bucyruss' BiomeTerrainMod)
        writeBigTitle("Cave settings");

        writeComment("This controls the odds that a given chunk will host a single cave and/or the start of a cave system.");
        writeValue(TerraCatalog.caveRarity.name(), this.caveRarity);
        writeNewLine();

        writeComment("The number of times the cave generation algorithm will attempt to create single caves and cave");
        writeComment("systems in the given chunk. This value is larger because the likelihood for the cave generation");
        writeComment("algorithm to bailout is fairly high and it is used in a randomizer that trends towards lower");
        writeComment("random numbers. With an input of 40 (default) the randomizer will result in an average random");
        writeComment("result of 5 to 6. This can be turned off by setting evenCaveDistribution (below) to true.");
        writeValue(TerraCatalog.caveFrequency.name(), this.caveFrequency);
        writeNewLine();

        writeComment("Sets the minimum and maximum altitudes at which caves will be generated. These values are");
        writeComment("used in a randomizer that trends towards lower numbers so that caves become more frequent");
        writeComment("the closer you get to the bottom of the map. Setting even cave distribution (above) to true");
        writeComment("will turn off this randomizer and use a flat random number generator that will create an even");
        writeComment("density of caves at all altitudes.");
        writeValue(TerraCatalog.caveMinAltitude.name(), this.caveMinAltitude);
        writeValue(TerraCatalog.caveMaxAltitude.name(), this.caveMaxAltitude);
        writeNewLine();

        writeComment("The odds that the cave generation algorithm will generate a single cavern without an accompanying");
        writeComment("cave system. Note that whenever the algorithm generates an individual cave it will also attempt to");
        writeComment("generate a pocket of cave systems in the vicinity (no guarantee of connection or that the cave system");
        writeComment("will actually be created).");
        writeValue(TerraCatalog.individualCaveRarity.name(), this.individualCaveRarity);
        writeNewLine();

        writeComment("The number of times the algorithm will attempt to start a cave system in a given chunk per cycle of");
        writeComment("the cave generation algorithm (see cave frequency setting above). Note that setting this value too");
        writeComment("high with an accompanying high cave frequency value can cause extremely long world generation time.");
        writeValue(TerraCatalog.caveSystemFrequency.name(), this.caveSystemFrequency);
        writeNewLine();

        writeComment("This can be set to create an additional chance that a cave system pocket (a higher than normal");
        writeComment("density of cave systems) being started in a given chunk. Normally, a cave pocket will only be");
        writeComment("attempted if an individual cave is generated, but this will allow more cave pockets to be generated");
        writeComment("in addition to the individual cave trigger.");
        writeValue(TerraCatalog.caveSystemPocketChance.name(), this.caveSystemPocketChance);
        writeNewLine();

        writeComment("The minimum and maximum size that a cave system pocket can be. This modifies/overrides the");
        writeComment("cave system frequency setting (above) when triggered.");
        writeValue(TerraCatalog.caveSystemPocketMinSize.name(), this.caveSystemPocketMinSize);
        writeValue(TerraCatalog.caveSystemPocketMaxSize.name(), this.caveSystemPocketMaxSize);
        writeNewLine();

        writeComment("Setting this to true will turn off the randomizer for cave frequency (above). Do note that");
        writeComment("if you turn this on you will probably want to adjust the cave frequency down to avoid long");
        writeComment("load times at world creation.");
        writeValue(TerraCatalog.evenCaveDistribution.name(), this.evenCaveDistribution);

        // Canyon settings
        writeBigTitle("Canyon settings");
        writeValue(TerraCatalog.canyonRarity.name(), this.canyonRarity);
        writeValue(TerraCatalog.canyonMinAltitude.name(), this.canyonMinAltitude);
        writeValue(TerraCatalog.canyonMaxAltitude.name(), this.canyonMaxAltitude);
        writeValue(TerraCatalog.canyonMinLength.name(), this.canyonMinLength);
        writeValue(TerraCatalog.canyonMaxLength.name(), this.canyonMaxLength);
        writeValue(TerraCatalog.canyonDepth.name(), this.canyonDepth);

        // Settings for BiomeMode:OldGenerator
        writeBigTitle("Settings for BiomeMode:OldGenerator");
        writeComment("This generator works only with old terrain generator!");
        writeValue(TerraCatalog.oldBiomeSize.name(), this.oldBiomeSize);
        writeValue(TerraCatalog.minMoisture.name(), this.minMoisture);
        writeValue(TerraCatalog.maxMoisture.name(), this.maxMoisture);
        writeValue(TerraCatalog.minTemperature.name(), this.minTemperature);
        writeValue(TerraCatalog.maxTemperature.name(), this.maxTemperature);

    }

    private void WriteCustomBiomes() throws IOException {
        String output = "";
        boolean first = true;
        for (String biome : CustomBiomes) {
            if (!first) {
                output += ",";
            }
            first = false;
            output += biome + ":" + CustomBiomeIds.get(biome);
        }
        writeValue(TerraCatalog.CustomBiomes.name(), output);

    }

    /**
     *
     * @return
     */
    public double getFractureHorizontal() {
        return this.fractureHorizontal < 0.0D ? 1.0D / (Math.abs(this.fractureHorizontal) + 1.0D) : this.fractureHorizontal + 1.0D;
    }

    /**
     *
     * @return
     */
    public double getFractureVertical() {
        return this.fractureVertical < 0.0D ? 1.0D / (Math.abs(this.fractureVertical) + 1.0D) : this.fractureVertical + 1.0D;
    }

    /**
     *
     * @param y
     *          <p/>
     * @return
     */
    public boolean createAdminium(int y) {
        return (!this.disableBedrock) && ((!this.flatBedrock) || (y == 0));
    }

    /**
     * @author Timethor
     */
    public enum TerrainMode {

        /**
         */
        Normal,
        /**
         */
        OldGenerator,
        /**
         */
        TerrainTest,
        /**
         */
        NotGenerate,
        /**
         */
        Default
    }

    /**
     * @author Timethor
     */
    public enum ImageMode {

        /**
         */
        Repeat,
        /**
         */
        ContinueNormal,
        /**
         */
        FillEmpty,
    }

    /**
     * @author Timethor
     */
    public enum ConfigMode {

        /**
         */
        WriteAll,
        /**
         */
        WriteDisable,
        /**
         */
        WriteWithoutComments,
    }

    /**
     *
     */
    public enum WriteMode {

        /**
         *
         */
        ReadOnly,
        /**
         *
         */
        Comments,
        /**
         *
         */
        Hints,
        /**
         *
         */
        Clean
    }

    /**
     *
     * @param stream
     *               <p/>
     * @throws IOException
     */
    public void Serialize(DataOutputStream stream) throws IOException {
        // General information
        writeStringToStream(stream, this.name);

        stream.writeInt(this.WorldFog);
        stream.writeInt(this.WorldNightFog);

        // Custom biomes + ids
        stream.writeInt(this.CustomBiomes.size());
        for (String biomeName : this.CustomBiomes) {
            writeStringToStream(stream, biomeName);
            stream.writeInt(this.CustomBiomeIds.get(biomeName));
        }

        // BiomeConfigs
        stream.writeInt(this.biomesCount);
        for (BiomeConfig config : biomeConfigs) {
            if (config == null) {
                continue;
            }
            stream.writeInt(config.Biome.getId());
            config.Serialize(stream);
        }
    }

    // Need for create world config from network packet
    /**
     *
     * @param stream
     * @param world
     *               <p/>
     * @throws IOException
     */
    public WorldConfig(DataInputStream stream, TerraWorld world) throws IOException {
        // General information
        this.name = readStringFromStream(stream);

        this.WorldFog = stream.readInt();
        this.WorldNightFog = stream.readInt();

        this.WorldFogR = ((WorldFog & 0xFF0000) >> 16) / 255F;
        this.WorldFogG = ((WorldFog & 0xFF00) >> 8) / 255F;
        this.WorldFogB = (WorldFog & 0xFF) / 255F;

        this.WorldNightFogR = ((WorldNightFog & 0xFF0000) >> 16) / 255F;
        this.WorldNightFogG = ((WorldNightFog & 0xFF00) >> 8) / 255F;
        this.WorldNightFogB = (WorldNightFog & 0xFF) / 255F;

        // Custom biomes + ids
        int count = stream.readInt();
        while (count-- > 0) {
            String configName = readStringFromStream(stream);
            int id = stream.readInt();
            world.AddBiome(configName, id);
            this.CustomBiomes.add(configName);
            this.CustomBiomeIds.put(configName, id);
        }

        // BiomeConfigs
        this.biomeConfigs = new BiomeConfig[world.getMaxBiomesCount()];

        count = stream.readInt();
        while (count-- > 0) {
            int id = stream.readInt();
            BiomeConfig config = new BiomeConfig(stream, this, world.getBiomeById(id));
            this.biomeConfigs[id] = config;
        }

    }
}
