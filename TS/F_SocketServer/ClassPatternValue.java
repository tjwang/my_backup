import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.pattern.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClassPatternValue extends PatternValue {

    DomainValue myDnIdx;
    public ClassPatternValue(Pattern patn, Line mLine, int sIdx, int eIdx, DomainValue myIdx)
    {
          super(patn, mLine, sIdx, eIdx);
          myDnIdx = myIdx;
    }

    public DomainValue getDomainValue()//  throws Exception
    {
        return myDnIdx;
    }
    
}
