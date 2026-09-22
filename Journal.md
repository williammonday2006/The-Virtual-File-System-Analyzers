# Phase1
The base case is when the item is a FileItem, which returns 1. The general case is when the item is a Folder, where the method recursively counts the files inside each child. Each recursive call works on an item inside the current folder, making the problem smaller. Eventually, the method reaches a FileItem and the recursion stops.
# Phase 2
If a folder contains only empty subfolders, each recursive call eventually returns null because no FileItem is found. Returning null represents that no file exists in that branch. Before comparing file sizes, the method checks that childLargest is not null, which prevents a NullPointerException.
