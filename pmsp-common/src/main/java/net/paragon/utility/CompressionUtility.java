/**
 * 
 */
package net.paragon.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.inject.Singleton;

import lombok.Builder;

/**
 * @author bqduc
 *
 */
@Builder
@Singleton
public class CompressionUtility {
	//private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	public byte[] compress(byte[] data) throws IOException {
		int count;
		byte[] output;
		Deflater deflater = new Deflater();

		deflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		deflater.finish();
		
		byte[] buffer = new byte[CommonConstants.BUFFER_SIZE];
		while (!deflater.finished()) {
			count = deflater.deflate(buffer); // returns the generated code... index
			outputStream.write(buffer, 0, count);
		}

		outputStream.close();
		output = outputStream.toByteArray();
		System.out.println("Original: " + data.length / CommonConstants.BUFFER_SIZE + " Kb");
		System.out.println("Compressed: " + output.length / CommonConstants.BUFFER_SIZE + " Kb");
		return output;

	}

	public byte[] decompress(byte[] data) throws IOException, DataFormatException {
		int count;
		byte[] output = null;
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[CommonConstants.BUFFER_SIZE];
		while (!inflater.finished()) {
			count = inflater.inflate(buffer);
			outputStream.write(buffer, 0, count);
		}

		outputStream.close();
		output = outputStream.toByteArray();
		//log.debug("Original: " + data.length);
		//log.debug("Compressed: " + output.length);
		return output;
	}

	public byte[] compressBytes(String name, byte[] inputBytes) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ZipOutputStream zos = new ZipOutputStream(baos);
    ZipEntry entry = new ZipEntry(name);
    entry.setSize(inputBytes.length);
    zos.putNextEntry(entry);
    zos.write(inputBytes);
    zos.closeEntry();
    zos.close();
    return baos.toByteArray();
	}

	public byte[] decompressBytes(String name, byte[] inputBytes) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ZipOutputStream zos = new ZipOutputStream(baos);
    ZipEntry entry = new ZipEntry(name);
    entry.setSize(inputBytes.length);
    zos.putNextEntry(entry);
    zos.write(inputBytes);
    zos.closeEntry();
    zos.close();
    return baos.toByteArray();
	}
}
