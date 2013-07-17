# Author of this World
    Author: String

# Description of what the object is supposed to be
    Description: String

# The TCW version, do not touch unless you know what you are doing.
    Version: [4.x.y]

Modes:{
    Write: [ReadOnly, Comments, Hints, Clean]
    Terrain: [Normal, OldGenerator, TerrainTest, NotGenerate, Default]
    Biome: [Normal, FromImage, OldGenerator, Default]
}

Biomes: {
    MaxChance:100
    Custom:{}
    Normal: {}
    Ice: {}
    Isle: {}
    Border: {}
    oldSize:1.5
}

Environment:{
    FogColor:{
        Day:0xc0d8ff
        Night:0x0b0d17
    }
    Moisture:{
        min:0.0
        max:1.0
    }
    Temperature:{
        min:0.0
        max:1.0
    }
    Water:{
        LevelMax:64
        LevelMin:0
    }
    Materials:{
        Water:9
        Ice:79
        Bedrock:7
    }
    ResourcesSeed:
    objectSpawnRatio:150
}



Terrain:{
    Zoom:10
    HeightBits: [5,6,7,8]
    StretchFactor:{
        Horizontal:0.0
        Vertical:0.0
    }
    SurfaceStone: [Remove, Keep, Minimize]
    Bedrock:{
        Floor:false
        FloorFlat: false
        Ceiling:false
        CeilingFlat:true
    }
    Image:{
        Mode: [FillEmpty, ContinueNormal, Repeat ]
        File: String
        FillBiome: [Biome Name]
        XOffset: [+/- Int]
        ZOffset: [+/- Int]
    }
}

Features:{
    Structures:{
        Stronghold:{
            Enabled:true
            Count:3
            Distance:32.0
            Spread:3
        }
        Village:{
            Enabled:true
            Size:0
            Distance:32
        }
        Rare:{
            Enabled:true
            MinSpread:9
            MaxSpread:32
        }
        Mineshaft:{
            Enabled:false
        }
        NetherFortress:{
            Enabled:false
        }
    }

    Natural:{
        Land:{
            Rarity:[1-100,95]
            Size: [0-TerrainZoom]
            Fuzzy: [0-(TerrainZoom-LandSize)]
        }
        Ice:{
            Rarity: [1-100,90]
            Size: [0-TerrainZoom,3]
            EnableOceans: [Boolean]
        }
        River:{
            Rarity: [0-TerrainZoom]
            Size: [0-(TerrainZoom-RiverRarity)]
            Enable: [Boolean]
        }
        Cave:{
            Rarity:7
            Frequency:40
            Altitude:{
                Min:8
                Max:128
            }
            individualRarity:25
            System:{
                Frequency:1
                PocketChance:0
                PocketMinSize:0
                PocketMaxSize:4
            }
            evenDistribution:false
        }
        Canyon:{
            Rarity:2
            Altitude:{
                Min:20
                Max:68
            }
            Length:{
                Min:84
                Max:112
            }
            Depth:3.0
        }
    }
}
