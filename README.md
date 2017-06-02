## component-app

Demonstartes a possible problem with component lifecycle methods.
Whenever there are inter-dependencies between components, i.e. one component depends on another the stop method needs to be called twice to free the nested resources. 
