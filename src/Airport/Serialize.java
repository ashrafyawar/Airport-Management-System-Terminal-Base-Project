package Airport;

import java.io.*;

/** Provides static methods to make objects serialized and deserialize them*/
public class Serialize
{

    /**
     * Serializes provided object.
     * Object should implement Serializable interface
     * @param fileName Name of the .ser file to be created
     * @param object Object to be serialized
     * @throws IOException Exception is thrown if there was an error writing to a file
     * */
    public static void serializeObject(String fileName, Object object) throws IOException
    {
        FileOutputStream outputFile = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputFile);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        outputFile.close();
    }

    /**
     * Deserializes and returns an object.
     * @param fileName Name of file including the object to be deserialized
     * @return Object being deserialized
     * @throws Exception If no such file exists, an exception is thrown
     */
    public static Object deserializeObject(String fileName) throws Exception
    {
        FileInputStream inputFile = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputFile);
        Object deserializedObject = objectInputStream.readObject();
        objectInputStream.close();
        inputFile.close();
        return deserializedObject;
    }
}
