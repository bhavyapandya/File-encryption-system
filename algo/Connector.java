package algo;

import javax.swing.JProgressBar;

/**<Font Color = "Green"><B>Accessed from <Font Color = "Red">AlgoFrame, PasswordDialog</Font>.
 *Takes password from Password dialog and sends it to Blow Fish_Algorithm
 *for preparing thekey.   Takes the data from AlgoFrame, divides the data present in the input file into 
 *different number of blocks according to the sizes selected
 *by the user and sends the divided blocks to Algorithm for
 *performing the actual operation.
 *</B></Font>
 *@see AlgoFrame
 *@see PasswordDialog
 *@see Blow Fish_Algorithm
 */

public class Connector
{
    public int BLOCK_SIZE=16;
    public int KEY_SIZE=16;
    JProgressBar pb;
    //  Buffer array stores the block data
    byte[] buffer ;
    //  Buffered indicates the number of bytes copied into the buffer array
    int buffered=0;
    boolean DEB=false;
    Object  sessionKey;
    int mode=-1;
    
    public Connector(JProgressBar pb1)
    {
            pb = pb1;
            pb.setValue(0);
            engineInit();
    }

    /**
     *Gets the data from AlgoFrame and calls doFinal for further
     *process.
     *@param in Input byte array to be decrypted.
     *@return decrypted byte array.
     */
    
    public byte[] decrypt(byte[] in ) throws Exception
    {
            mode = 1;
            return doFinal( in , 0 , in.length);
    }

    /**
     *Gets the data from AlgoFrame and calls doFinal for further
     *process.
     *@param in Input byte array to be encrypted.
     *@return encrypted byte array.
     */
    
    public byte[] encrypt(byte[] in ) throws Exception
    {
            mode = 0;
            return doFinal( in , 0 ,  in.length );
    }

    /**
     *Gets the data from AlgoFrame and calls doFinal for further
     *process.
     *@param in Input byte array to be encrypted.
     *@param offset The starting position of data to be encrypted.
     *@param length The number of bytes to be encrypted.
     *@return encrypted byte array.
     */
    
    public byte[] encrypt(byte[] in, int offset, int length) throws Exception
    {
            mode = 0;
            return doFinal(in, offset, length);
    }
    
    /**
     *Gets the data from AlgoFrame and calls doFinal for further
     *process.
     *@param in Input byte array to be decrypted.
     *@param offset The starting position of data to be decrypted.
     *@param length The number of bytes to be decrypted.
     *@return decrypted byte array.
     */
    
    public byte[] decrypt(byte[] in, int offset, int length) throws Exception
    {
            mode = 1;
            return doFinal(in, offset, length);
    }
    
    /**
     *Called by encrypt or decrypt functions and will transfer
     *the input data to doUPdate.
     *@param in Input byte array to be converted.
     *@param inOff The starting position of data to be converted.
     *@param inLen The number of bytes to be converted.
     *@return converted byte array.
     */
    
    public byte[] doFinal (byte[] in, int inOff, int inLen)
    {
            byte[] out = this.doUpdate(in, inOff, inLen);
            return out;
    }

    /**This divides the input byte array into number of blocks 
     * and sends each block to the algorithm for encryption 
     * or decryption.
     *@param in Input byte array to be converted.
     *@param inOff The starting position of data to be converted.
     *@param inLen The number of bytes to be converted.
     *@return converted byte array.
     */
    
    public byte[] doUpdate(byte[] in, int inOff, int inLen)
    {
            byte[] out =  new byte[BLOCK_SIZE * ((inLen + buffered) / BLOCK_SIZE)];
            //	outOff indicates the position from where we have to write the 
            //	converted data in to the out array.
            int outOff = 0;
            byte[] temp = null;
            for (int i = 0; i < inLen; i++)
            {
                    buffer[buffered++] = in[inOff++];
                    if (buffered >= BLOCK_SIZE)
                    {
                            pb.setValue( (inOff/inLen)*100 );
                            if(mode==0)
                                    temp = BlowFish_Algorithm.blockEncrypt(buffer, 0, sessionKey, BLOCK_SIZE);
                            else
                                    temp = BlowFish_Algorithm.blockDecrypt(buffer, 0, sessionKey, BLOCK_SIZE);
                            engineInit();
                            System.arraycopy(temp, 0, out, outOff, temp.length);
                            if(DEB)
                                    System.out.println(":" + new String(temp));
                            outOff += temp.length;
                    }
            }
            return out;
    }

    /**
     *Initializes the progress bar of the AlgoFrame.
     */
    
    private void engineInit()
    {
            if(DEB)
                    System.out.println("Eng INIT");
            buffer = new byte[ BLOCK_SIZE] ;
            for (int i = 0; i < BLOCK_SIZE; )
                    buffer[i++] = 0;
            buffered = 0;
    }

    /**
     *Called by the AlgoFrame and calls makekey function of
     *Blow Fish_Algorithm for producing round keys in a single
     *array Session key.   This will be stored in Rijndeal_Algorithm
     *only.
     */
    
    public void setKey(byte[] key) throws Exception
    {
            if( !(key.length ==16 || key.length== 24 || key.length== 32) )
                    throw new IllegalArgumentException("Invalid Key");
            sessionKey = BlowFish_Algorithm.makeKey(key);
    }
}
