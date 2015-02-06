package com.timethor.terracontrol.core.builtin;

import com.timethor.terracontrol.core.configuration.BiomeConfig;
import com.timethor.terracontrol.core.configuration.TerraSetting;
import com.timethor.terracontrol.core.configuration.WorldConfig;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Timethor
 */
public enum TerraCatalog implements TerraSetting {
    // Files

    //>>	Plugin Specific
    /**
     *
     */
    PluginConfigDirectoryName("TerraControl"),
    /**
     *
     */
    PluginConfigName("TerraControl"),
    //>>	Globally Included
    /**
     *
     */
    PluginObjectsDirectoryName("GlobalObjects"),
    /**
     *
     */
    PluginBiomesDirectoryName("GlobalBiomes"),
    /**
     *
     */
    PluginConfigExtension("plugin.tc"),
    //>>	World Included
    /**
     *
     */
    WorldObjectsDirectoryName("WorldObjects"),
    /**
     *
     */
    WorldBiomesDirectoryName("WorldBiomes"),
    /**
     *
     */
    WorldConfigExtension("world.tc"),
    //>>	Biome Included
    /**
     *
     */
    BiomeObjectsDirectoryExtension("biome.incl"),
    /**
     *
     */
    BiomeConfigExtension("biome.tc"),
    // End files

    // Network
    /**
     *
     */
    ChannelName("TerraControl"),
    /**
     *
     */
    ProtocolVersion(5),
    // End network

    /**
     *
     */
    snowAndIceMaxTemp(0.15F),
    // World settings
    /**
     *
     */
    SettingsMode(WorldConfig.WriteMode.Comments),
    /**
     *
     */
    TerrainMode(WorldConfig.TerrainMode.Normal),
    /**
     *
     */
    BiomeMode("Normal"),
    /**
     *
     */
    WorldHeightBits(7),
    /**
     *
     */
    GenerationDepth(10),
    /**
     *
     */
    BiomeRarityScale(100),
    /**
     *
     */
    LandRarity(97),
    /**
     *
     */
    LandSize(0),
    /**
     *
     */
    LandFuzzy(6),
    /**
     *
     */
    IceRarity(90),
    /**
     *
     */
    IceSize(3),
    /**
     *
     */
    RiverRarity(4),
    /**
     *
     */
    RiverSize(0),
    /**
     *
     */
    RiversEnabled(true),
    /**
     *
     */
    FrozenOcean(true),
    /**
     *
     */
    NormalBiomes("Desert,Forest,Extreme Hills,Swampland,Plains,Taiga,Jungle", SettingsType.StringArray),
    /**
     *
     */
    IceBiomes("Ice Plains", SettingsType.StringArray),
    /**
     *
     */
    IsleBiomes("MushroomIsland,Ice Mountains,DesertHills,ForestHills,TaigaHills,River,JungleHills", SettingsType.StringArray),
    /**
     *
     */
    BorderBiomes("MushroomIslandShore,Beach,Extreme Hills Edge", SettingsType.StringArray),
    /**
     *
     */
    CustomBiomes("", SettingsType.StringArray),
    /**
     *
     */
    ImageMode(WorldConfig.ImageMode.Repeat),
    /**
     *
     */
    ImageFile("map.png"),
    /**
     *
     */
    ImageFillBiome("Ocean"),
    /**
     *
     */
    ImageXOffset(0),
    /**
     *
     */
    ImageZOffset(0),
    /**
     *
     */
    oldBiomeSize(1.5D),
    /**
     *
     */
    minMoisture(0.0f),
    /**
     *
     */
    maxMoisture(1.0f),
    /**
     *
     */
    minTemperature(0.0f),
    /**
     *
     */
    maxTemperature(1.0f),
    /**
     *
     */
    WorldFog("0xC0D8FF", SettingsType.Color),
    /**
     *
     */
    WorldNightFog("0x0B0D17", SettingsType.Color),
    /**
     *
     */
    NetherFortressesEnabled(false),
    /**
     *
     */
    StrongholdsEnabled(true),
    /**
     *
     */
    StrongholdCount(3),
    /**
     *
     */
    StrongholdDistance(32.0),
    /**
     *
     */
    StrongholdSpread(3),
    /**
     *
     */
    VillagesEnabled(true),
    /**
     *
     */
    VillageDistance(32),
    /**
     *
     */
    VillageSize(0),
    /**
     *
     */
    VillageType(BiomeConfig.VillageType.disabled),
    /**
     *
     */
    MineshaftsEnabled(true),
    /**
     *
     */
    MineshaftRarity(1D),
    /**
     *
     */
    RareBuildingsEnabled(true),
    /**
     *
     */
    MinimumDistanceBetweenRareBuildings(9),
    /**
     *
     */
    MaximumDistanceBetweenRareBuildings(32),
    /**
     *
     */
    RareBuildingType(BiomeConfig.RareBuildingType.disabled),
    /**
     *
     */
    caveRarity(7),
    /**
     *
     */
    caveFrequency(40),
    /**
     *
     */
    caveMinAltitude(8),
    /**
     *
     */
    caveMaxAltitude(128),
    /**
     *
     */
    individualCaveRarity(25),
    /**
     *
     */
    caveSystemFrequency(1),
    /**
     *
     */
    caveSystemPocketChance(0),
    /**
     *
     */
    caveSystemPocketMinSize(0),
    /**
     *
     */
    caveSystemPocketMaxSize(4),
    /**
     *
     */
    evenCaveDistribution(false),
    /**
     *
     */
    canyonRarity(2),
    /**
     *
     */
    canyonMinAltitude(20),
    /**
     *
     */
    canyonMaxAltitude(68),
    /**
     *
     */
    canyonMinLength(84),
    /**
     *
     */
    canyonMaxLength(112),
    /**
     *
     */
    canyonDepth(3.0D),
    /**
     *
     */
    WaterLevelMax(63),
    /**
     *
     */
    WaterLevelMin(0),
    /**
     *
     */
    WaterBlock(9),
    /**
     *
     */
    IceBlock(79),
    /**
     *
     */
    FractureHorizontal(0.0D),
    /**
     *
     */
    FractureVertical(0.0D),
    /**
     *
     */
    DisableBedrock(false),
    /**
     *
     */
    CeilingBedrock(false),
    /**
     *
     */
    FlatBedrock(false),
    /**
     *
     */
    BedrockobBlock(7),
    /**
     *
     */
    RemoveSurfaceStone(false),
    /**
     *
     */
    objectSpawnRatio(1),
    /**
     *
     */
    ResourcesSeed(0L), // "L" means that it is a long instead of an int

    // End world settings

    // Biome settings
    /**
     *
     */
    BiomeSize(5),
    /**
     *
     */
    BiomeRarity(100),
    /**
     *
     */
    BiomeColor("", SettingsType.Color),
    /**
     *
     */
    RiverBiome("River"),
    /**
     *
     */
    IsleInBiome("Ocean", SettingsType.StringArray),
    /**
     *
     */
    BiomeIsBorder("", SettingsType.StringArray),
    /**
     *
     */
    NotBorderNear("", SettingsType.StringArray),
    /**
     *
     */
    BiomeTemperature(0.5F),
    /**
     *
     */
    BiomeWetness(0.5F),
    /**
     *
     */
    ReplaceToBiomeName(""),
    /**
     *
     */
    BiomeHeight(0.1D),
    /**
     *
     */
    BiomeVolatility(0.3D),
    /**
     *
     */
    SurfaceBlock(2),
    /**
     *
     */
    GroundBlock(3),
    /**
     *
     */
    UseWorldWaterLevel(true),
    /**
     *
     */
    SkyColor("0x7BA5FF", SettingsType.Color),
    /**
     *
     */
    WaterColor("0xFFFFFF", SettingsType.Color),
    /**
     *
     */
    GrassColor("0x000000", SettingsType.Color),
    /**
     *
     */
    GrassColorIsMultiplier(true),
    /**
     *
     */
    FoliageColor("0x000000", SettingsType.Color),
    /**
     *
     */
    FoliageColorIsMultiplier(true),
    /**
     *
     */
    Volatility1(0.0D),
    /**
     *
     */
    Volatility2(0.0D),
    /**
     *
     */
    VolatilityWeight1(0.5D),
    /**
     *
     */
    VolatilityWeight2(0.45D),
    /**
     *
     */
    DisableBiomeHeight(false),
    /**
     *
     */
    MaxAverageHeight(0.0D),
    /**
     *
     */
    MaxAverageDepth(0.0D),
    /**
     *
     */
    CustomHeightControl("0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0", SettingsType.StringArray),
    // End biome settings

    // Resource settings

    /**
     *
     */
    SmallLakeWaterFrequency(4),
    /**
     *
     */
    SmallLakeLavaFrequency(2),
    /**
     *
     */
    SmallLakeWaterRarity(7),
    /**
     *
     */
    SmallLakeLavaRarity(3),
    /**
     *
     */
    SmallLakeMinAltitude(8),
    /**
     *
     */
    SmallLakeMaxAltitude(120),
    /**
     *
     */
    undergroundLakeFrequency(2),
    /**
     *
     */
    undergroundLakeRarity(5),
    /**
     *
     */
    undergroundLakeMinSize(50),
    /**
     *
     */
    undergroundLakeMaxSize(60),
    /**
     *
     */
    undergroundLakeMinAltitude(0),
    /**
     *
     */
    undergroundLakeMaxAltitude(50),
    /**
     *
     */
    dungeonRarity(100),
    /**
     *
     */
    dungeonFrequency(8),
    /**
     *
     */
    dungeonMinAltitude(0),
    /**
     *
     */
    dirtDepositRarity(100),
    /**
     *
     */
    dirtDepositFrequency(20),
    /**
     *
     */
    dirtDepositSize(32),
    /**
     *
     */
    dirtDepositMinAltitude(0),
    /**
     *
     */
    dirtDepositMaxAltitude(128),
    /**
     *
     */
    gravelDepositRarity(100),
    /**
     *
     */
    gravelDepositFrequency(10),
    /**
     *
     */
    gravelDepositSize(32),
    /**
     *
     */
    gravelDepositMinAltitude(0),
    /**
     *
     */
    gravelDepositMaxAltitude(128),
    /**
     *
     */
    clayDepositRarity(100),
    /**
     *
     */
    clayDepositFrequency(1),
    /**
     *
     */
    clayDepositSize(32),
    /**
     *
     */
    clayDepositMinAltitude(0),
    /**
     *
     */
    clayDepositMaxAltitude(128),
    /**
     *
     */
    coalDepositRarity(100),
    /**
     *
     */
    coalDepositFrequency(20),
    /**
     *
     */
    coalDepositSize(16),
    /**
     *
     */
    coalDepositMinAltitude(0),
    /**
     *
     */
    coalDepositMaxAltitude(128),
    /**
     *
     */
    ironDepositRarity(100),
    /**
     *
     */
    ironDepositFrequency(20),
    /**
     *
     */
    ironDepositSize(8),
    /**
     *
     */
    ironDepositMinAltitude(0),
    /**
     *
     */
    ironDepositMaxAltitude(64),
    /**
     *
     */
    goldDepositRarity(100),
    /**
     *
     */
    goldDepositFrequency(2),
    /**
     *
     */
    goldDepositSize(8),
    /**
     *
     */
    goldDepositMinAltitude(0),
    /**
     *
     */
    goldDepositMaxAltitude(32),
    /**
     *
     */
    redstoneDepositRarity(100),
    /**
     *
     */
    redstoneDepositFrequency(8),
    /**
     *
     */
    redstoneDepositSize(7),
    /**
     *
     */
    redstoneDepositMinAltitude(0),
    /**
     *
     */
    redstoneDepositMaxAltitude(16),
    /**
     *
     */
    diamondDepositRarity(100),
    /**
     *
     */
    diamondDepositFrequency(1),
    /**
     *
     */
    diamondDepositSize(7),
    /**
     *
     */
    diamondDepositMinAltitude(0),
    /**
     *
     */
    diamondDepositMaxAltitude(16),
    /**
     *
     */
    lapislazuliDepositRarity(100),
    /**
     *
     */
    lapislazuliDepositFrequency(1),
    /**
     *
     */
    lapislazuliDepositSize(7),
    /**
     *
     */
    lapislazuliDepositMinAltitude(0),
    /**
     *
     */
    lapislazuliDepositMaxAltitude(16),
    /**
     *
     */
    emeraldDepositRarity(100),
    /**
     *
     */
    emeraldDepositFrequency(1),
    /**
     *
     */
    emeraldDepositSize(5),
    /**
     *
     */
    emeraldDepositMinAltitude(4),
    /**
     *
     */
    emeraldDepositMaxAltitude(32),
    /**
     *
     */
    waterClayDepositRarity(100),
    /**
     *
     */
    waterClayDepositSize(4),
    /**
     *
     */
    waterSandDepositRarity(100),
    /**
     *
     */
    waterSandDepositFrequency(4),
    /**
     *
     */
    waterSandDepositSize(7),
    /**
     *
     */
    roseDepositRarity(100),
    /**
     *
     */
    roseDepositMinAltitude(0),
    /**
     *
     */
    roseDepositMaxAltitude(128),
    /**
     *
     */
    flowerDepositRarity(100),
    /**
     *
     */
    flowerDepositMinAltitude(0),
    /**
     *
     */
    flowerDepositMaxAltitude(128),
    /**
     *
     */
    redMushroomDepositRarity(100),
    /**
     *
     */
    redMushroomDepositMinAltitude(0),
    /**
     *
     */
    redMushroomDepositMaxAltitude(128),
    /**
     *
     */
    brownMushroomDepositRarity(100),
    /**
     *
     */
    brownMushroomDepositMinAltitude(0),
    /**
     *
     */
    brownMushroomDepositMaxAltitude(128),
    /**
     *
     */
    longGrassDepositRarity(100),
    /**
     *
     */
    deadBushDepositRarity(100),
    /**
     *
     */
    pumpkinDepositRarity(3),
    /**
     *
     */
    pumpkinDepositFrequency(1),
    /**
     *
     */
    pumpkinDepositMinAltitude(0),
    /**
     *
     */
    pumpkinDepositMaxAltitude(128),
    /**
     *
     */
    reedDepositRarity(100),
    /**
     *
     */
    reedDepositMinAltitude(0),
    /**
     *
     */
    reedDepositMaxAltitude(128),
    /**
     *
     */
    cactusDepositRarity(100),
    /**
     *
     */
    cactusDepositMinAltitude(0),
    /**
     *
     */
    cactusDepositMaxAltitude(128),
    /**
     *
     */
    vinesRarity(100),
    /**
     *
     */
    vinesFrequency(50),
    /**
     *
     */
    vinesMinAltitude(64),
    /**
     *
     */
    waterSourceDepositRarity(100),
    /**
     *
     */
    waterSourceDepositFrequency(20),
    /**
     *
     */
    waterSourceDepositMinAltitude(8),
    /**
     *
     */
    waterSourceDepositMaxAltitude(128),
    /**
     *
     */
    lavaSourceDepositRarity(100),
    /**
     *
     */
    lavaSourceDepositFrequency(10),
    /**
     *
     */
    lavaSourceDepositMinAltitude(8),
    /**
     *
     */
    lavaSourceDepositMaxAltitude(128);
    // End resource settings
    /**
     */
    private int iValue;
    /**
     */
    private long lValue;
    /**
     */
    private double dValue;
    /**
     */
    private float fValue;
    /**
     */
    private String sValue;
    /**
     */
    private boolean bValue;
    /**
     */
    private Enum<?> eValue;
    /**
     */
    private SettingsType returnType;
    /**
     */
    private ArrayList<String> sArrayValue;
    /**
     */
    private ArrayList<Integer> iArrayValue;

    private TerraCatalog(int i) {
        this.iValue = i;
        this.returnType = SettingsType.Int;
    }

    private TerraCatalog(double d) {
        this.dValue = d;
        this.returnType = SettingsType.Double;
    }

    private TerraCatalog(float f) {
        this.fValue = f;
        this.returnType = SettingsType.Float;
    }

    private TerraCatalog(long l) {
        this.lValue = l;
        this.returnType = SettingsType.Long;
    }

    private TerraCatalog(String s) {
        this.sValue = s;
        this.returnType = SettingsType.String;
    }

    private TerraCatalog(String s, SettingsType type) {
        this.returnType = type;

        if (type == SettingsType.StringArray) {
            this.sArrayValue = new ArrayList<>();
            if (s.contains(",")) {
                Collections.addAll(this.sArrayValue, s.split(","));
            } else if (!s.equals("")) {
                this.sArrayValue.add(s);
            }
            return;
        }
        if (type == SettingsType.IntArray) {
            this.iArrayValue = new ArrayList<>();
            if (s.contains(",")) {
                for (String string : s.split(",")) {
                    iArrayValue.add(Integer.valueOf(string));
                }
            } else if (!s.equals("")) {
                this.iArrayValue.add(Integer.valueOf(s));
            }
            return;
        }
        this.sValue = s;

    }

    private TerraCatalog(Enum<?> e) {
        this.eValue = e;
        this.returnType = SettingsType.Enum;

    }

    private TerraCatalog(boolean b) {
        this.bValue = b;
        this.returnType = SettingsType.Boolean;
    }

    /**
     *
     * @return
     */
    @Override
    public int intValue() {
        return this.iValue;
    }

    /**
     *
     * @return
     */
    @Override
    public long longValue() {
        return this.lValue;
    }

    /**
     *
     * @return
     */
    @Override
    public double doubleValue() {
        return this.dValue;
    }

    /**
     *
     * @return
     */
    @Override
    public float floatValue() {
        return this.fValue;
    }

    /**
     *
     * @return
     */
    @Override
    public Enum<?> enumValue() {
        return this.eValue;
    }

    /**
     * @return
     */
    @Override
    public SettingsType getReturnType() {
        return this.returnType;
    }

    /**
     *
     * @return
     */
    @Override
    public String stringValue() {
        return this.sValue;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<String> stringArrayListValue() {
        return this.sArrayValue;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean booleanValue() {
        return this.bValue;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Integer> intArrayListValue() {
        return this.iArrayValue;
    }
}