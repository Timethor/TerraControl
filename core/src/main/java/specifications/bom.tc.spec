###########################################################################
#  True                                y,yes,on,true
#  False                               n,no,off,false
#  Integer                             +/-256
#  FP                                  +/-.inf,.nan,2.56e+2
#  String                              String, "String", 'String'
#  Named Array                         Array{[,]+}
#  Array                               {[,]+}
#  Vector                              <,[,]+>
#  Key-Value Pair                      Key:Value
#  
#  
#  [-] <-- Value Possibilities              
#       Ex: [0-255] => Expected Values are 0 through 255
#  [Orientation] = [North, South, East, West]
#  [Boolean] = [True or False]
#  % = [0-100]



##+-----------------------------------------------------------------+##
# |                            BO4 object                           | #
##+-----------------------------------------------------------------+##
##| This is the config file for a custom object                     |##
##+-----------------------------------------------------------------+##

# Author of this object
    Author: String

# Description of what the object is supposed to be
    Description: String

# The BO version, do not touch unless you know what you are doing.
    Version: [4.x.y]

# Tells the plugin how to handle the settings within this file
#     ReadOnly - The plugin will only read from this file. Will not update
#   old config settings, use with care. Use this to save custom comments.
#     Comments - This will re-write the entire file with verbose comments.
#     Hints - This will re-write the entire file with short comments.
#     Clean - This will re-write the entire file with no comments.
    SettingsMode: [ReadOnly, Comments, Hints, Clean]

##+-----------------------------------------------------------------+##
# |                         Spawn settings                          | #
##+-----------------------------------------------------------------+##
##+ When and How the object spawns within the world                 +##
##+-----------------------------------------------------------------+##

Spawn: {
    Frequency: [1-65535]                                                    @Spawn_Frequency
    Chance: %                                                               @Spawn_Chance
    As: {[Tree, Object, Structure, Sapling]+}                               @Spawn_As
    Rotation: {                                         
        Orientation: [Random, Static]                                       @Spawn_Orientation
        Allow:  {[Orientation]+}                                            @Spawn_Allow_Direction
    }
    Vertical: {
        Find: [Random, Highest, Lowest, Central ]                           @Spawn_Vertical_Mode
        Orientation: [Grounded, Hanging]
        Offset: [0-100]
        Variance: [0-100]
        RequireSolidGround: [Boolean]                                       @Spawn_Vertical_Solid
        Min: [0-BO4:Max]                                                    @Spawn_Vertical_Min
        Avg: [BO4:Min-BO4:Max]                                              @Spawn_Vertical_Avg
        Max: [BO4:Min-World:Max]                                            @Spawn_Vertical_Max
    }
    Protection: {
        FormOutline: [Boolean]
        OutlineMaterial: 
    }
    Prevent{
        Block:{
            CheckBlockAt:{                                                      @Spawn_Check_Block_At_Location
                <x,y,z>{@Block_Group}([Include, Exclude]),
                ...
                <source>{@Block_Group}([Include, Exclude]),
                <child>{@Block_Group}([Include, Exclude]),
            },
            CheckLightAt:{                                                      @Spawn_Check_Light_At_Location
                <x,y,z>{Min,Max}
                ...
                <source>{Min,Max}
                <child>{Min,Max}
            },
        }
        Object:{
            CheckBlockAt:{                                                      @Spawn_Check_Block_At_Location
                <x,y,z>{@Block_Group}([Include, Exclude]),
                @Block_Group:{
                    <x,y,z>,<x,y,z>,<x,y,z>,([Include, Exclude])
                }
                ...
                <source>{@Block_Group}([Include, Exclude]),
                <child>{@Block_Group}([Include, Exclude]),
            }
            CheckLightAt:{                                                      @Spawn_Check_Light_At_Location
                <x,y,z>{Min,Max},
                ...
                <source>{Min,Max},
                <child>{Min,Max},
            }
        }
    }
}


##+-----------------------------------------------------------------+##
# |                       Branch Declarations                       | #
##+-----------------------------------------------------------------+##
Branches: {
    Chance: %                                                               @Branch_Chance
        <x,y,z>{                                                            @Branch_Vector_Marker
            Branch1, [Orientation], %;                                      @Branch_Declaration_File
            Branch2, [Orientation], %;
            @Lambda-Name, [Orientation], %                                  @Branch_Declaration_Lambda
        }(0-255[,Total%]), 
    ...
}

##+-----------------------------------------------------------------+##
# |                        Data Declarations                        | #
##+-----------------------------------------------------------------+##
Data: {
    <x,y,z>{ID:Data,%;ID:Data,%;ID:Data,%},
    <x,y,z>{ID:Data},
    <x,y,z>{@VarName1},
    @VarName2:{
        <x,y,z>,<x,y,z>,<x,y,z>,<x,y,z>,<x,y,z>,<x,y,z>
    }
}

##+-----------------------------------------------------------------+##
# |                   Lambda Branch Declarations                    | #
##+-----------------------------------------------------------------+##
Lambda-Branches: {
    Lambda-Name:{                                                           @Branch_Declaration_Lambda
            Data: {
                <x,y,z>{ID:Data},                                           @Block_Declaration_Basic
                <x,y,z>{ID:Data,%},                                         @Block_Declaration_Chance
                <x,y,z>{ID:Data,file.nbt},                                  @Block_Declaration_NBT
                <x,y,z>{ID:Data,%;ID:Data,%;ID:Data,%},                     @Block_Declaration_Multi_Type
                <x,y,z>{@VarName1},                                         @Block_Declaration_Block_Variables
                ...
            }
            Branches: {
                <x,y,z>{                                                    @Branch_Vector_Marker
                    Branch1, [Orientation], %;                              @Branch_Declaration_File
                    Branch2, [Orientation], %;
                    @Lambda-Name, [Orientation], %                          @Branch_Declaration_Lambda
                }(0-255[,Total%]),
            ...
            }
    }
    ...
}

##+-----------------------------------------------------------------+##
# |                         Block Grouping                          | #
##+-----------------------------------------------------------------+##
CompositionGroups: {                                                        See @Block_Composition_Group
    VarName1:{ID:Data},                                                     and @Block_Groups
}

##+-----------------------------------------------------------------+##
# |                         FoundIn Settings                        | #
##+-----------------------------------------------------------------+##
FoundIn: {
    Files: {                                                                @File_Inclusion_Exclusion
        File1, File2, File3, File4}                                         @File_Format
    } ([Include, Exclude])
    NameSpaces: {                                                           @Namespace_Inclusion_Exclusion
        Namespace1, Namespace2.subspace1                                    @Namespace_Format
    } ([Include, Exclude])
}




@Block_groups
    
    Block groups are contained in curly brackets, { and }.

    Simple Format:
        
        Will include a block of specified ID with default data value 100% of the time
        {ID}
        Will include a block of specified ID and data value 100% of the time
        {ID:Data}
        Will include a block of specified ID with an x% chance to spawn
        {ID,x%}
        Will include a block of specified ID with a weighted spawn value of x
        {ID,x#}
        Will include a block of specified ID with attached NBT data from file.nbt
        {ID,file.nbt}
    Multi-Block Format:
        Will include the blocks listed with x, y, and z % chances to spawn consecutively
        {ID,x%;ID:Data,y%,file.nbt;ID:Data,z%}
        Will include the blocks listed with x, y, and z weights to spawn consecutively
        {ID;ID:Data,file.nbt;ID:Data,z#}
    Super Examples:
        Chance-Based:
            {2,12%; 3,12%; 5:1,5%; 5:2,5%; 5:3,5%; 91:4,10%; 54:2,superchest.nbt,90%; 146:2,90%,trapped.nbt; 130:2,100%}
                This example will try to spawn:
                    - A grass block and give it a 12% chance to spawn, if that doesn't spawn a new random is made and then try to spawn
                    - A dirt block and give it a 12% chance to spawn, if not... new random ... spawn
                    - A Spruce plank block and give it a 5% chance to spawn, if not... new random ... spawn
                    - A Birch plank block and give it a 5% chance to spawn, if not... new random ... spawn
                    - A Jungle plank block and give it a 5% chance to spawn, if not... new random ... spawn
                    - A Jack-o-lantern block with no face and give it a 10% chance to spawn, if not... new random ... spawn
                    - A south facing Chest with data from superchest.nbt and give it a 90% chance to spawn, if not... new random ... spawn
                    - A south facing Trapped Chest with data from trapped.nbt and give it a 90% chance to spawn, if not... new random ... spawn
                    - A south facing Ender Chest and give it a 100% chance to spawn
                Essentially, each item has its own individual chance to spawn with a certain percent.
                The key here is individual chance, each block gets a new random number from 0-100

            {2,12%; 3,12%; 5:1,5%; 5:2; 5:3,5%}
                Same as above, but shortened. 
                --- Notice there is no % on the fourth entry ---
                For Chance-Based block groups, if you leave a percent off an entry, 
                the plugin will assume you are terminating the group and fill in 100% for you
                So, in this case, the 5:3 block entry at the end with a 5% chance will 
                NEVER spawn since the 5:2 block entry is guaranteed to spawn.
            
        Weight-Based:
            {2,12#; 3,12#; 5:1,5#; 5:2,5#; 5:3,5#; 91:4,10#; 54:2,superchest.nbt,90#; 146:2,90#,trapped.nbt; 130:2,100#}
            This example is the same as the first chance-based example except instead of % signs, we will use the # sign:
            When this line is loaded a total number is accumulated based on the #'s, for this example it would be:
            12+12+5+5+5+10+90+90+100 = 329, and a number from 0 to 328 is generated
            Then the blocks are spread onto a spectrum giving the following:
           + Block      Range needed         Approx. Chance +
           |            to Spawn                            |
           +------------------------------------------------+
           | 2          0-11                 3.64%          |
           | 3          12-23                3.64%          |
           | 5:1        24-28                1.52%          |
           | 5:2        29-33                1.52%          |
           | 5:3        34-38                1.52%          |
           | 91:4       39-48                3.04%          |
           | 54:2       49-138               27.36%         |
           | 146:2      139-228              27.36%         |
           | 130:2      229-328              30.40%         |
           +------------------------------------------------+
            Where ever the random number lands is what is spawned, so if our random was 100, the 54:2 entry would
            spawn which is a south facing Chest with data from superchest.nbt

                {2,12#; 3,12#; 5:1,5#; 5:2; 5:3,5#}
                Same as above, but shortened. 
                --- Notice there is no # on the fourth entry ---
                For Weight-Based block groups, if you leave a weight off an entry, 
                the plugin will assume you want a basic weight of 1#
                So, in this case, the 5:2 block will have a 1 in (12+12+5+1+5 = 35) chance to spawn
                There is no terminating effect like chance-based groups in weight-based groups
            
@Block_Groups_Named
    Using the above block group possibilities, you can make complex groups 
    and give them a name to save time and space in your files.
    The format is simple:
        GroupName:{Block_Group_Data}
    
    To use this named group, instead of the usual group data, used an @ sign and include the name.
    For the above "GroupName" it would look like {@GroupName}

@Block_Vector_Marker
    This is a fancy name for a block group at a location
    The format is:
        <x,y,z>{Block_Group_Data} or
        <x,y,z>{@GroupName}

@Block_Vector_Check_Marker
    An extension of @Block_Vector_Markers
    The format includes the ability to include or exclude blocks to check for and is:
    <x,y,z>{Block_Group_Data}([Include, Exclude]) or
    <x,y,z>{@GroupName}([Include, Exclude])
