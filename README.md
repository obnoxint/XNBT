# XNBT

XNBT is an extensible implementation of NBT which was originally written by Markus Persson and implemented into the game
Minecraft. NBT stands for Named Binary Tag. It is a binary datatype which is used to serialize and deserialize
structured data.

Minecraft is being considered the reference implementation of NBT and XNBT attempts to be compatible with Minecrafts
implementation.

NBT supports the following default data types (excluding "end"): byte, short, integer, long, double, byte array, integer
array, string, NBT tag list, NBT compound (Map like structure) 

## Using XNBT

XNBT provides input and output stream classes with compression support and utility functions for reading and writing NBT
tags.

### Using the utility functions to read or write NBT tags

The XNBT class holds all utility functions for reading and writing NBT tags. All reading functions return a List with
NBTTags and all writing functions require a List with NBTTags which must not contain null elements or end tags. An end
tag will always be appended to output streams by these functions. If you need to avoid this, use IO streams instead.

Example for reading a list of tags from a file:

    File file = new File("my.nbt");
    List<NBTTag> list = XNBT.readFromFile(file);
    
    for (NBTTag tag : list) {
        System.out.println(tag.getHeader().getName());
    }

Example for writing a list of tags to a file:

    File file = new File("my.nbt");
    List<NBTTag> list = new ArrayList<>();
    CompoundTag compound = new CompoundTag("address", null); // initialize empty CompoundTag
    
    StringTag firstName = new StringTag("firstName", "Max");
    StringTag lastName = new StringTag("lastName", "Mustermann");
    StringTag street = new StringTag("street", "Musterstraﬂe");
    StringTag houseNumber = new StringTag("houseNumber", "12a");
    IntegerTag zip = new IntegerTag ("zip", 12345);
    StringTag city = new StringTag("city", "Musterstadt");
    
    compound.put(firstName);
    compound.put(lastName);
    compound.put(street);
    compound.put(houseNumber);
    compound.put(zip);
    compound.put(city);
    
    list.add(compound);
    XNBT.writeToFile(list, file);
