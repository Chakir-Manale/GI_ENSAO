import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

public class DOMFiltre implements NodeFilter {

	@Override
	public short acceptNode(Node n) {
		// TODO Auto-generated method stub
		if (n.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) n;
			if (!e.getNodeName().equals("a")) {
				return FILTER_SKIP;
			}
			if (e.hasAttribute("name")) {
				return FILTER_ACCEPT;
			}
		}
		return FILTER_SKIP;
	}

}
