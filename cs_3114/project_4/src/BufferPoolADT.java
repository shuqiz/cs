/**
 * ADT for a buffer pool manager.
 *
 * @author Elmer Landaverde
 * @version Nov 4, 2013
 */
public interface BufferPoolADT
{

	/**
	 * The size of this file referenced by this buffer pool, measured in bytes.
	 *
	 * @return The length of the file in bytes.
	 */
	public long getFileSize();


	/**
	 * Increases the size of the file by the number of blocks specified in the
	 * parameter. In oder words if the current file has a size of 5 blocks, the
	 * calling incFileSize(5) will result in a file that has a size of 10
	 * blocks.
	 *
	 * @param blocks
	 *            The number of blocks to increase the file size by.
	 * @throws Exception
	 *             The file size could not be modified.
	 */
	public void incFileSize(int blocks)
		throws Exception;


	/**
	 * Clears all the resources that were reserved for this buffer pool.
	 *
	 * @return True if the all the resources where successfully freed.
	 * @throws Exception
	 *             File closing exception.
	 */
	public boolean closeBufferPool()
		throws Exception;


	/**
	 * Takes the byte array given and writes the bytes to the file position
	 * specified by 'pos'. If the position or the byte array is invalid this
	 * method will not write anything to disk.
	 *
	 * @param src
	 *            The byte to be written.
	 * @param pos
	 *            The position where the bytes to be written start at.
	 * @return True if the bytes where successfully written to the buffer. False
	 *         if they were not written to disk.
	 * @throws Exception
	 *             Could not write to file.
	 */
	public boolean setBytes(byte[] src, long pos)
		throws Exception;


	/**
	 * Copies the bytes in the file position specified by 'pos' to the provided
	 * byte array. NOTE: If the position or the byte array is invalid, then no
	 * byte will be read from the buffer.
	 *
	 * @param dst
	 *            The byte array where the bytes will be copied to.
	 * @param pos
	 *            The starting position of the byte to be copied to the array.
	 * @return True if the bytes where copied to the byte array successfully.
	 *         False if the byte could not be read or written to the byte array.
	 * @throws Exception
	 *             Could not read file.
	 */
	public boolean getBytes(byte[] dst, long pos)
		throws Exception;


	/**
	 * Gets the size measured in byte of each buffer.
	 *
	 * @return The size of each buffer.
	 */
	public long getBufferSize();
}