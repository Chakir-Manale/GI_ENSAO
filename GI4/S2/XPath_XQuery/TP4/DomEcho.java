
/*
 * @(#)DomEcho.java	1.9 98/11/10
 *
 * Copyright (c) 1998 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.FactoryConfigurationError;  
import javax.xml.parsers.ParserConfigurationException;
 
import org.xml.sax.SAXException;  
import org.xml.sax.SAXParseException;  

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.DOMException;

// Basic GUI components
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

// GUI components for right-hand side
import javax.swing.JSplitPane;
import javax.swing.JEditorPane;

// GUI support classes
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// For creating borders
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

// For creating a TreeModel
import javax.swing.tree.*;
import javax.swing.event.*;
import java.util.*;

public class DomEcho  extends JPanel
{
    // Global value so it can be ref'd by the tree-adapter
    static Document document; 

    static final int windowHeight = 460;
    static final int leftWidth = 300;
    static final int rightWidth = 340;
    static final int windowWidth = leftWidth + rightWidth;

    public DomEcho()
    {
       // Make a nice border
       EmptyBorder eb = new EmptyBorder(5,5,5,5);
       BevelBorder bb = new BevelBorder(BevelBorder.LOWERED);
       CompoundBorder cb = new CompoundBorder(eb,bb);
       this.setBorder(new CompoundBorder(cb,eb));

       // Set up the tree
       JTree tree = new JTree(new DomToTreeModelAdapter());

       // Iterate over the tree and make nodes visible
       // (Otherwise, the tree shows up fully collapsed)
       //TreePath nodePath = ???;
       //  tree.expandPath(nodePath); 

       // Build left-side view
       JScrollPane treeView = new JScrollPane(tree);
       treeView.setPreferredSize(  
           new Dimension( leftWidth, windowHeight ));

       // Build right-side view
       JEditorPane htmlPane = new JEditorPane("text/html","");
       htmlPane.setEditable(false);
       JScrollPane htmlView = new JScrollPane(htmlPane);
       htmlView.setPreferredSize( 
           new Dimension( rightWidth, windowHeight ));

       // Build split-pane view
       JSplitPane splitPane = 
          new JSplitPane( JSplitPane.HORIZONTAL_SPLIT,
                          treeView,
                          htmlView );
       splitPane.setContinuousLayout( true );
       splitPane.setDividerLocation( leftWidth );
       splitPane.setPreferredSize( 
            new Dimension( windowWidth + 10, windowHeight+10 ));

       // Add GUI components
       this.setLayout(new BorderLayout());
       this.add("Center", splitPane );
    } // constructor

    public static void main(String argv[])
    {
        if (argv.length != 1) {
            System.err.println("Usage: java DomEcho filename");
            System.exit(1);
        }

        DocumentBuilderFactory factory =
            DocumentBuilderFactory.newInstance();

        try {
           DocumentBuilder builder = factory.newDocumentBuilder();
           document = builder.parse( new File(argv[0]) );
            makeFrame();
 
        } catch (SAXException sxe) {
           // Error generated during parsing)
           Exception  x = sxe;
           if (sxe.getException() != null)
               x = sxe.getException();
           x.printStackTrace();

        } catch (ParserConfigurationException pce) {
            // Parser with specified options can't be built
            pce.printStackTrace();

        } catch (IOException ioe) {
           // I/O error
           ioe.printStackTrace();
        }
    } // main

    public static void makeFrame() {
        // Set up a GUI framework
        JFrame frame = new JFrame("DOM Echo");
        frame.addWindowListener(
          new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
          }  
        );

        // Set up the tree, the views, and display it all
        final DomEcho echoPanel = 
           new DomEcho();
        frame.getContentPane().add("Center", echoPanel );
        frame.pack();
        Dimension screenSize = 
           Toolkit.getDefaultToolkit().getScreenSize();
        int w = windowWidth + 10;
        int h = windowHeight + 10;
        frame.setLocation(screenSize.width/3 - w/2, 
                          screenSize.height/2 - h/2);
        frame.setSize(w, h);
        frame.setVisible(true);
    } // makeFrame


    // An array of names for DOM node-types
    // (Array indexes = nodeType() values.)
    static final String[] typeName = {
        "none",
        "Element",
        "Attr",
        "Text",
        "CDATA",
        "EntityRef",
        "Entity",
        "ProcInstr",
        "Comment",
        "Document",
        "DocType",
        "DocFragment",
        "Notation",
    };

    // This class wraps a DOM node and returns the text we want to
    // display in the tree. It also returns children, index values,
    // and child counts.
    public class AdapterNode 
    { 
      org.w3c.dom.Node domNode;

      // Construct an Adapter node from a DOM node
      public AdapterNode(org.w3c.dom.Node node) {
        domNode = node;
      }

      // Return a string that identifies this node in the tree
      // *** Refer to table at top of org.w3c.dom.Node ***
      public String toString() {
        String s = typeName[domNode.getNodeType()];
        String nodeName = domNode.getNodeName();
        if (! nodeName.startsWith("#")) {
           s += ": " + nodeName;
        }
        if (domNode.getNodeValue() != null) {
           if (s.startsWith("ProcInstr")) 
              s += ", "; 
           else 
              s += ": ";
           // Trim the value to get rid of NL's at the front
           String t = domNode.getNodeValue().trim();
           int x = t.indexOf("\n");
           if (x >= 0) t = t.substring(0, x);
           s += t;
        }
        return s;
      }


      /*
       * Return children, index, and count values
       */
      public int index(AdapterNode child) {
        //System.err.println("Looking for index of " + child);
        int count = childCount();
        for (int i=0; i<count; i++) {
          AdapterNode n = this.child(i);
          if (child.domNode == n.domNode) return i;
        }
        return -1; // Should never get here.
      }

      public AdapterNode child(int searchIndex) {
        //Note: JTree index is zero-based. 
        org.w3c.dom.Node node = 
             domNode.getChildNodes().item(searchIndex);
        return new AdapterNode(node); 
      }

      public int childCount() {
          return domNode.getChildNodes().getLength();  
      }
    }

    // This adapter converts the current Document (a DOM) into 
    // a JTree model. 
    public class DomToTreeModelAdapter 
      implements javax.swing.tree.TreeModel 
    {
      // Basic TreeModel operations
      public Object  getRoot() {
        //System.err.println("Returning root: " +document);
        return new AdapterNode(document);
      }
      public boolean isLeaf(Object aNode) {
        // Determines whether the icon shows up to the left.
        // Return true for any node with no children
        AdapterNode node = (AdapterNode) aNode;
        if (node.childCount() > 0) return false;
        return true;
      }
      public int     getChildCount(Object parent) {
        AdapterNode node = (AdapterNode) parent;
        return node.childCount();
      }
      public Object getChild(Object parent, int index) {
        AdapterNode node = (AdapterNode) parent;
        return node.child(index);
      }
      public int getIndexOfChild(Object parent, Object child) {
        AdapterNode node = (AdapterNode) parent;
        return node.index((AdapterNode) child);
      }
      public void valueForPathChanged(TreePath path, Object newValue) {
        // Null. We won't be making changes in the GUI
        // If we did, we would ensure the new value was really new,
        // adjust the model, and then fire a TreeNodesChanged event.
      }

      /*
       * Use these methods to add and remove event listeners.
       * (Needed to satisfy TreeModel interface, but not used.)
       */
      private Vector listenerList = new Vector();
      public void addTreeModelListener(TreeModelListener listener) {
        if ( listener != null 
        && ! listenerList.contains( listener ) ) {
           listenerList.addElement( listener );
        }
      }
      public void removeTreeModelListener(TreeModelListener listener) {
        if ( listener != null ) {
           listenerList.removeElement( listener );
        }
      }

      // Note: Since XML works with 1.1, this example uses Vector.
      // If coding for 1.2 or later, though, I'd use this instead:
      //   private List listenerList = new LinkedList();
      // The operations on the List are then add(), remove() and
      // iteration, via:
      //  Iterator it = listenerList.iterator();
      //  while ( it.hasNext() ) {
      //    TreeModelListener listener = (TreeModelListener) it.next();
      //    ...
      //  }

      /*
       * Invoke these methods to inform listeners of changes.
       * (Not needed for this example.)
       * Methods taken from TreeModelSupport class described at 
       *   http://java.sun.com/products/jfc/tsc/articles/jtree/index.html
       * That architecture (produced by Tom Santos and Steve Wilson)
       * is more elegant. I just hacked 'em in here so they are
       * immediately at hand.
       */
      public void fireTreeNodesChanged( TreeModelEvent e ) {
        Enumeration listeners = listenerList.elements();
        while ( listeners.hasMoreElements() ) {
          TreeModelListener listener = 
            (TreeModelListener) listeners.nextElement();
          listener.treeNodesChanged( e );
        }
      } 
      public void fireTreeNodesInserted( TreeModelEvent e ) {
        Enumeration listeners = listenerList.elements();
        while ( listeners.hasMoreElements() ) {
           TreeModelListener listener =
             (TreeModelListener) listeners.nextElement();
           listener.treeNodesInserted( e );
        }
      }   
      public void fireTreeNodesRemoved( TreeModelEvent e ) {
        Enumeration listeners = listenerList.elements();
        while ( listeners.hasMoreElements() ) {
          TreeModelListener listener = 
            (TreeModelListener) listeners.nextElement();
          listener.treeNodesRemoved( e );
        }
      }   
      public void fireTreeStructureChanged( TreeModelEvent e ) {
        Enumeration listeners = listenerList.elements();
        while ( listeners.hasMoreElements() ) {
          TreeModelListener listener =
            (TreeModelListener) listeners.nextElement();
          listener.treeStructureChanged( e );
        }
      }
    }
}
