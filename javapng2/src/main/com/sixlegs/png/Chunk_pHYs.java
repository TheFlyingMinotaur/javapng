/*
com.sixlegs.image.png - Java package to read and display PNG images
Copyright (C) 1998-2005 Chris Nokleberg

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Library General Public
License as published by the Free Software Foundation; either
version 2 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Library General Public License for more details.

You should have received a copy of the GNU Library General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
*/

package com.sixlegs.png;

import java.io.IOException;
import java.util.Map;

class Chunk_pHYs
extends PngChunk
{
    public Chunk_pHYs()
    {
        super(pHYs);
    }

    public void read(PngInputStream in, int length, PngImage png)
    throws IOException
    {
        checkLength(length, 9);
        int pixelsPerUnitX = in.readInt();
        int pixelsPerUnitY = in.readInt();
        int unit = in.readUnsignedByte();
        if (unit != PngImage.UNIT_UNKNOWN && unit != PngImage.UNIT_METER)
            throw new PngWarning("Illegal pHYs chunk unit specifier: " + unit);

        Map props = png.getProperties();
        props.put(PngImage.PIXELS_PER_UNIT_X, Integers.valueOf(pixelsPerUnitX));
        props.put(PngImage.PIXELS_PER_UNIT_Y, Integers.valueOf(pixelsPerUnitY));
        props.put(PngImage.UNIT, Integers.valueOf(unit));
    }    
}
