import numpy as np

dist = float(input("distance: ")) #distance from center of base of hoop on ground

angle = 1.22173 #in radians
hoop = 2.4384 #height of hood from ground
step = .0005 #Euler's method delta x.  smaller values increase accuracy but raise calculation time
c = 0.47 #drag coefficient
p = 1.04 #air density
a = (np.pi)*(0.3**2) #cross-sectional area
mu = (c*p*a)/2 #drag force *DO NOT CHANGE*
g = 9.807 #gravity constant *DO NOT CHANGE WHILE ON EARTH*



def Deviance(vr): #calculates horizontal distance from center of ball to center of hoop at time of hoop vertical alignment
    x = -dist #local reset of dist
    y = 0 #starting height
    vx = np.cos(angle)*vr #translation from polar to cartesian
    vy = np.sin(angle)*vr #translation from polar to cartesian
    toggle = 0 #counter keeps track when ball passes hoop vertically
    error = -1 #keep this negative. only needed if ball doesnt reach hoop height
    while y >= 0:
       
        #system of differential equations.  Euler's method
        x += step*vx
        y += step*vy
        vx += step*(-mu*vx*np.sqrt((vx**2)+(vy**2)))
        vy += step*((-g)-mu*vy*np.sqrt((vx**2)+(vy**2)))

        #keeps track of ball passing hoop height
        if y <= hoop and toggle == 1:
            toggle += 1
            error = x
        if y >= hoop and toggle == 0:
            toggle += 1

    return error #negative if ball is short, positive if ball is far

def Final_vr(accuracy, left_bound, right_bound): #determines best velocity through guessing
    test_vr = (right_bound+left_bound)/2 #initial guess
    for each in range(accuracy): #accuracy determines # of guesses
        if Deviance(test_vr) > 0: #if guess is too large
            right_bound = (right_bound + left_bound)/2 #change right bound to guess
        else:
            left_bound = (right_bound + left_bound)/2 #if guess is too small
        test_vr = (right_bound + left_bound)/2 #change left bound to guess
    return test_vr #best guess

while True:
    print(Final_vr(25, 0, 150)) #output
    dist = float(input("distance: ")) #run again?