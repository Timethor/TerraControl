##+-----------------------------------------------------------------+##
# |                          Biome Settings                         | #
##+-----------------------------------------------------------------+##
Biome: {
    Size: 7
    Rarity: 98
    Color: 0x665522
    EffectiveBiome: InGameNameOfBiome
    Rivers: {Biome1, Biome2}
    Islands: {Biome1, Biome2}
    Borders: {Biome1, Biome2}
    NotBorders: {Biome1, Biome2}
    
    Temperature:0.8
    Wetness:0.4
    
    Height: {
        Enabled: [Boolean]
        Factor: -1.65
        Average:{
            Min:
            Max:
        }
        CustomControl: {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0}
    }
        
    Volatility: {
        Main: 0.6
        Alt1: {Value, Weight}
        Alt2: {Value, Weight}
    }
    
    
    Files: {[Include, Exclude]}                                             @File_Inclusion_Exclusion
        {File1, File2, File3, File4}                                        @File_Format
    
    NameSpaces: {[Include, Exclude]}{                                       @Namespace_Inclusion_Exclusion
        Namespace1, Namespace2.subspace1                                    @Namespace_Format
    }
}

Blocks: {
    File: StoneRack.blocks
    Surface:
    Ground:
    Replace: {From,To,MinH,MaxH}
    Water: {
        UseWorld: [Boolean]
        Min:
        Max:
        Block:
    }
    Ice : {
        Min:
        Max:
        Block:
    }
}

Coloring:{
    File: RedSky.color
    Sky:0x7ba5ff
    Water:0xffffff
    Grass:0xffffff
    GrassIsMultiplier:true
    Foliage:0xffffff
    FoliageIsMultiplier:true
}

Resources:{
    File: plains.resource
    SmallLake(Block[:Data],Frequency,Rarity,MinAltitude,MaxAltitude)
    Dungeon(Frequency,Rarity,MinAltitude,MaxAltitude)
    UnderGroundLake(MinSize,MaxSize,Frequency,Rarity,MinAltitude,MaxAltitude)
    Ore(Block[:Data],Size,Frequency,Rarity,MinAltitude,MaxAltitude,BlockSource[,BlockSource2,BlockSource3.....])
    UnderWaterOre(Block[:Data],Size,Frequency,Rarity,BlockSource[,BlockSource2,BlockSource3.....])
    CustomObject(Object[,AnotherObject[,...]])
    CustomStructure([Object,Object_Chance[,AnotherObject,Object_Chance[,...]]])
    Tree(Frequency,TreeType,TreeType_Chance[,Additional_TreeType,Additional_TreeType_Chance.....])
    Sapling()
    Plant(Block[:Data],Frequency,Rarity,MinAltitude,MaxAltitude,BlockSource[,BlockSource2,BlockSource3.....])
    Grass(Block,BlockData,Frequency,Rarity,BlockSource[,BlockSource2,BlockSource3.....])
    Reed(Block[:Data],Frequency,Rarity,MinAltitude,MaxAltitude,BlockSource[,BlockSource2,BlockSource3.....])
    Cactus(Block[:Data],Frequency,Rarity,MinAltitude,MaxAltitude,BlockSource[,BlockSource2,BlockSource3.....])
    Liquid(Block[:Data],Frequency,Rarity,MinAltitude,MaxAltitude,BlockSource[,BlockSource2,BlockSource3.....])
    AboveWaterRes(Block[:Data],Frequency,Rarity)
    Vines(Frequency,Rarity,MinAltitude,MaxAltitude)
    Vein(Block[:Data],MinRadius,MaxRadius,Rarity,OreSize,OreFrequency,OreRarity,MinAltitude,MaxAltitude,BlockSource[,BlockSource2,..])
    Well(BaseBlock[:Data],HalfSlabBlock[:Data],WaterBlock[:Data],Frequency,Rarity,MinAltitude,MaxAltitude,BlockSource[,BlockSource2,..])
}


Parent: Parent-Name

Strongholds:true
NetherFortresses:true
VillageType:
MineshaftRarity:1.0
RareBuildingType:disabled

Spawn:{
    File: TerrorSquad.spawn
    Monsters: []
    Creatures: []
}

