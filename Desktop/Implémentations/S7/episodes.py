import numpy as np
#Episode détérministe

def episode_deterministe(env, policy, max_steps=200) :
    s , _ = env.reset() #état initial pour l'env : S0
    episodes = []
    for _ in range (max_steps) :
        a = policy(s) if callable(policy) else int(policy[s]) #choisit l'action a
        s_next, r, terminated, truncated , _ = env.step(a)
        episodes.append((s,a,r))
        s = s_next
        if terminated or truncated :
            break
        
    return episodes


#Episode stochastique
def episode_stochastic(env, policy, max_steps=200) :
    s , _ = env.reset() #état initial pour l'env : S0
    episodes = []
    for _ in range (max_steps) :
        probas = policy[s]
        a = np.random.choice(len(probas), p=probas) # chosiir uen distribution de probas pour les actions
        s_next, r, terminated, truncated , _ = env.step(a)
        episodes.append((s,a,r))
        s = s_next
        if terminated or truncated :
            break
        
    return episodes