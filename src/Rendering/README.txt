This package contains the code required for direct rendering of a frame and/or sequence of frames
Camera class
Let 's introduce a simplification of this kind,
1) The direction of the main beam of the camera will be strictly parallel to the XY plane
    otherwise, many other calculations will have to be performed
2) Human vision is limited vertically from -70(-75) to 55 (60)
to simplify, we will assume that looking up and down are limited equally
3) The direction of the main beam of the camera is fixed by coordinates (1,0,0)