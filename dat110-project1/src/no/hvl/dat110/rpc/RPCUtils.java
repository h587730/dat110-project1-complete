package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		byte[] encoded;

		// TODO: marshall RPC identifier and string into byte array

		byte[] stringAsBytes = str.getBytes();
		
		encoded = new byte[stringAsBytes.length+1];
		
		encoded[0] = rpcid;
		
		System.arraycopy(stringAsBytes, 0, encoded, 1, stringAsBytes.length);

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		String decoded;

		// TODO: unmarshall String contained in data into decoded
		
		byte[] withoutID = new byte[data.length-1];
				
		System.arraycopy(data, 1, withoutID, 0, withoutID.length);

		decoded = new String(withoutID);

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded;

		// TODO: marshall RPC identifier in case of void type

		encoded = new byte[1]; 
				
		encoded[0] = rpcid;

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		// TODO: unmarshall void type
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		byte[] encoded;

		// TODO: marshall RPC identifier and string into byte array
		
		byte[] intAsBytes = new byte[4];
		
		//Omgjør int x til en byte sekvens
		intAsBytes = ByteBuffer.allocate(4).putInt(x).array();
		
		encoded = new byte[5];
		
		//Larger rpcid som første byte 
		encoded[0] = rpcid;
		
		//Kopierer intAsBytes over på encoded
		System.arraycopy(intAsBytes, 0, encoded, 1, 4);
				
		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		int decoded;

		// TODO: unmarshall integer contained in data

		byte[] intAsBytes = new byte[4];
		
		//Kopierer bytesekvens fra data
		System.arraycopy(data, 1, intAsBytes, 0, 4);
		
		//Lagrer bytesekvens fra data som int
		decoded = ByteBuffer.wrap(intAsBytes).getInt();

		return decoded;

	}
}
