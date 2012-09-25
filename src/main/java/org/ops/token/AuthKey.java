package org.ops.token;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class AuthKey {
    int version = 1;
    int hashAlgorithm = 0;
    int sigAlgorithm = 0;
    byte[] signature = null;
    byte[] data = null;

    public void readData(DataInputStream ois) throws IOException,ClassNotFoundException
    {
        System.out.println("readObject");

	int header = ois.readInt();
        version = ois.readInt();
        hashAlgorithm = ois.readInt();
        sigAlgorithm = ois.readInt();

        int length = 0;

        length = ois.readInt();
        byte[] bufSig = new byte[length];
        if (length > 0) {
            ois.read(bufSig, 0, length);
        }

        length = ois.readInt();
        byte[] bufData = new byte[length];
        if (length > 0) {
            ois.read(bufData, 0, length);
        }
    }
 
    public void writeData(DataOutputStream oos) throws IOException
    {
        System.out.println("writeObject");

        oos.writeInt(0xA1B2C3D4);	
        oos.writeInt(version);
        oos.writeInt(hashAlgorithm);
        oos.writeInt(sigAlgorithm);
        oos.writeInt(signature.length);
        oos.write(signature);
        oos.writeInt(data.length);
        oos.write(data);
    }
}

