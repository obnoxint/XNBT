# XNBT (WIP)

XNBT is an extensible implementation of NBT which was originally written by Markus Persson and implemented into the game
Minecraft. NBT stands for Named Binary Tag. It is a binary datatype which is used to serialize and deserialize
structured data.

Minecraft is being considered the reference implementation of NBT and XNBT attempts to be compatible with Minecrafts
implementation.

## NBT

NBT uses the following default data types (excluding "end"): byte, short, integer, long, double, byte array, integer
array, string, NBT tag list, NBT compound (Map like structure) 

In NBT so called tags are used as data containers. The binary representation of most tags look similar to this:

1. (byte) type id
- (String) tag name
- binary payload of variable length

The type of the payload of a tag is specified by the type id. The name can be empty. The payload is optional in some
cases.

There is no standard format for NBT files. Minecraft compatible files usually are either gzipped or uncompressed and
contain a single CompoundTag (used as a root tag), optionally followed by an EndTag.

XNBT can write any number and type of NBTTag on the root level of the file.

## XNBT Features

- Supports all default tag types
- IO handling separate from type structure
- Supports custom types and extension of default types
- Serialize and deserialize any object using the Tag annotation

## Default Types

| Id | name | contains |
| -- | ---- | -------- |
| 0 | EndTag | nothing |
| 1 | ByteTag | byte |
| 2 | ShortTag | short |
| 3 | IntegerTag | int |
| 4 | LongTag | long |
| 5 | FloatTag | float |
| 6 | DoubleTag | double |
| 7 | ByteArrayTag | byte[] |
| 8 | StringTag | String |
| 9 | ListTag | List<NBTTag> |
| 10 | CompoundTag | Map<String, NBTTag> |
| 11 | IntegerArrayTag | int[] |

The *EndTag* denotes the end of the payload of CompoundTags and of the file. It shouldn't be used programmatically.

The *ListTag* is a list of elements of a specific type of NBTTag and implements List<NBTTag>. The type of the elements
of a ListTag is defined by the first element in the list. Clearing the list will allow another type of element to be
added. The ListTag will write the type of its elements and the number of elements followed by only the payload of its
elements, not their ids or names.

The *CompoundTag* implements Map<String, NBTTag>. The key of the generic interface can be ignored since all key names
are specified by their NBTTags. The CompoundTag will write a continious stream of NBTTags finished by and EndTag.

## Using XNBT

XNBT provides input and output stream classes with compression support and utility functions for reading and writing NBT
tags.

The XNBT class holds utility functions for reading and writing NBT tags.

### Using the utility functions to read or write NBT tags

 All reading functions return a List with
NBTTags and all writing functions require a List with NBTTags which must not contain null elements or end tags. An end
tag will always be appended to output streams by these functions. If you need to avoid this, use IO streams instead.

Reading a list of tags from a file:

```java
List<NBTTag> list = XNBT.readFromFile(new File("my.nbt"));
```

Example for writing a list of tags to a file:

```java
List<NBTTag> contacts = new ArrayList<>();

CompoundTag contact1 = new CompoundTag("contact");
contact1.put(new StringTag("nick", "Jenny");
contact1.put(new StringTag("email", "jenny@example.com");

CompoundTag contact2 = new CompoundTag("contact");
contact2.put(new StringTag("nick", "Tom");
contact2.put(new StringTag("email", "tom@example.com");

list.add(contact1);
list.add(contact2);
XNBT.writeToFile(list, new File("my.nbt"));
```