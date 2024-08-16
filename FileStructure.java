/*
 * File structure class that sets up the structure of the files
 * @author Dikran Kahiaian
 */
import java.util.ArrayList;
import java.util.Iterator;

public class FileStructure {
	// instance variables
	private NLNode<FileObject>root;
	// constructor
	public FileStructure(String fileObjectName) throws FileObjectException {
			// takes file name and makes an object and explores that folder
			FileObject object= new FileObject(fileObjectName);
			root=new NLNode(object, null);
			
				exploreFile(root);
		
	}
	// explore the folder
	private void exploreFile(NLNode<FileObject> r){
		// get the data inside the folder
			FileObject f=r.getData();
			if (f.isFile()) {
				return;
			}
			// iterator to be able to go through each folder
			
			Iterator<FileObject> fileItr= f.directoryFiles();
			// go through each file
			while(fileItr.hasNext()) {
				FileObject fileObj = fileItr.next();
				// add that file to the tree
				NLNode<FileObject> n= new NLNode(fileObj,r);
				r.addChild(n);
				// recursive call to explore inside of the file
				exploreFile(n);
			}
			
	}
	
	public Iterator<String> filesOfType(String type){
		return (fileType(root, type));
	}
	
	public Iterator<String> fileType(NLNode<FileObject> r, String type){
		// make an array list to store the absolute path
		ArrayList<String> container = new ArrayList<>();
		FileObject f = r.getData();
		// check if the file contains the type
		if (f.getLongName().contains(type)) {
			// add the absolute path
			container.add(f.getLongName());
		}
		// children iterator to be able to iterate through each children
		Iterator<NLNode<FileObject>> children = r.getChildren();
		// iterate through each children
		while (children.hasNext()) {
			NLNode<FileObject> n = children.next();
			// recursive call to check if the children files contain any files with the type
			Iterator<String> childFiles = fileType(n, type);
			while (childFiles.hasNext()) {
				//add all the containers of the child files that have the type inside one container
				container.add(childFiles.next());
			}
		}
		// returning the iterator of container to be able to iterate through
		return container.iterator();
	}
	public String findFile(String name) {
		return findFileName(root, name);
	}
	private String findFileName(NLNode<FileObject> r, String name) {
		
		FileObject f=r.getData();
		// check if the names match
		if (f.isFile() && f.getName().equals(name)){;
			return f.getLongName();
			// check if f is a folder
		}else if (f.isDirectory()) {
			// check the files inside of the folder
			Iterator<FileObject> files = f.directoryFiles();
			// go through each file inside the folder
	        while (files.hasNext()) {
            NLNode<FileObject> child = new NLNode<>(files.next(), r);
            String result = findFileName(child, name);
            if (!result.isEmpty()) {
                return result;
            }
        }
    }
		
    return "";
	}
	public NLNode<FileObject> getRoot(){
		return root;
	}
}
