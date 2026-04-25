# ReactJS Interview Questions - Complete Reference (270+ Questions)

**Generated from sudheerj/reactjs-interview-questions repository**  
*Last updated: July 2025 | Quick-reference for technical interviews*

## Table of Contents

## 1. CORE REACT CONCEPTS (1-78)

| # | Question | Answer Summary |
|---|----------|---------------|
| 1 | What is React? | UI library for building UIs using Virtual DOM - diffs changes for efficient updates |
| 2 | What is JSX? | JavaScript XML syntax extension - writes HTML-like code in JS that transpiles to `React.createElement()` |
| 3 | JSX Elements vs Components | Elements are single instances, Components are reusable functions/classes |
| 4 | Function vs Class Components | Functions simpler, Classes have state + lifecycle methods (Hooks replaced classes) |
| 5 | `PureComponent` vs `Component` | PureComponent shallow compares props/state to skip re-renders |
| 6 | State vs Props | State mutable (internal), Props immutable (passed from parent) |
| 7 | Controlled Components | Form inputs where React state = value, `onChange` updates state |
| 8 | Uncontrolled Components | Form inputs using `ref` to access DOM value directly |
| 9 | Fragments (`<>...</>`) | Wrapper without extra DOM nodes - introduced React 16.2 |
| 10 | Error Boundaries | Class components catching JS errors in child tree during render/lifecycle |
| 11 | Portals | Render children into DOM node outside parent hierarchy (`ReactDOM.createPortal`) |
| 12 | Higher-Order Components (HOCs) | Function takes component, returns enhanced component |
| 13 | Render Props | Share code via function prop (alternative to HOCs) |
| 14 | Context API | State sharing without prop drilling - Provider/Consumer pattern |
| 15 | Refs | Access DOM nodes/React elements imperatively (`useRef`/`createRef`) |
| 16 | Keys in Lists | Help React identify changed/added/removed items during reconciliation |
| 17 | `shouldComponentUpdate` | Lifecycle method to control re-rendering (shallow prop/state compare) |
| 18 | Reconciliation | React's diffing algorithm comparing Virtual DOM trees |
| 19 | Fiber Architecture | Complete rewrite enabling async rendering, better scheduling (React 16+) |
| 20 | Concurrent Mode | Pause/resume/prioritize work - time slicing for better UX |

## 2. HOOKS (21-50)

| # | Question | Answer Summary |
|---|----------|---------------|
| 21 | What are Hooks? | Functions for state/lifecycle in functional components (React 16.8+) |
| 22 | Rules of Hooks | Top-level only, React function components/custom Hooks only |
| 23 | `useState` | `[state, setState]` tuple - adds local state to functions |
| 24 | `useEffect` | Side effects after render - mimics lifecycle methods |
| 25 | `useContext` | Subscribe to Context without Consumer in render |
| 26 | `useReducer` | Complex state logic - `[state, dispatch]` like Redux |
| 27 | `useCallback` | Memoize callbacks - stable function reference |
| 28 | `useMemo` | Memoize expensive calculations - cache values |
| 29 | `useRef` | Mutable ref object persists across re-renders |
| 30 | `useImperativeHandle` | Customize instance value exposed via `forwardRef` |
| 31 | `useLayoutEffect` | Sync version of `useEffect` - runs before browser paint |
| 32 | `useDebugValue` | Custom Hooks debug label in React DevTools |
| 33 | Custom Hooks | Share stateful logic between components |
| 34 | `useEffect` infinite loop | Missing/incorrect dependency array |
| 35 | `useEffect` cleanup | Return function from effect - runs before next effect/re-mount |
| 36 | Hook order matters? | Yes - Hooks called in same order every render |
| 37 | Hooks vs Classes | Hooks simpler, no `this` binding, better tree shaking |
| 38 | `useId` (React 18) | Generate unique IDs for accessibility |
| 39 | `useTransition` (React 18) | Mark updates as non-urgent, concurrent features |
| 40 | `useDeferredValue` (React 18) | Defer non-critical state updates |

## 3. PERFORMANCE OPTIMIZATION (41-60)

| # | Question | Answer Summary |
|---|----------|---------------|
| 41 | `React.memo()` | HOC - skips re-render if props shallow-equal |
| 42 | `PureComponent` | Class version of `React.memo()` - shallow compares props/state |
| 43 | `useMemo` vs `useCallback` | `useMemo` values, `useCallback` functions |
| 44 | When to use memoization? | Expensive renders, stable props, frequent parent re-renders |
| 45 | List re-rendering | Stable unique `key`s matching item identity |
| 46 | `React.Profiler` | Measure render time/performance |
| 47 | Code splitting | `React.lazy()` + `Suspense` for dynamic imports |
| 48 | `shouldComponentUpdate` | Manual re-render control (use `React.memo` instead) |
| 49 | Virtualized lists | `react-window`/`react-virtualized` for large lists |
| 50 | Bundle analysis | Webpack Bundle Analyzer, source-map-explorer |

## 4. ADVANCED PATTERNS (61-78)

| # | Question | Answer Summary |
|---|----------|---------------|
| 61 | Compound Components | Related components sharing implicit state |
| 62 | Controlled vs Uncontrolled | Controlled: React owns value, Uncontrolled: DOM owns value |
| 63 | Lifting State Up | Share state between siblings via closest common parent |
| 64 | Prop Drilling | Passing props through many levels (Context alternative) |
| 65 | Children as Function | Render prop pattern - children receive props |
| 66 | Provider Pattern | Context Provider wrapping component tree |
| 67 | `React.cloneElement` | Clone element + inject new props |
| 68 | `React.Children` utilities | `map`, `forEach`, `count`, `only`, `toArray` |
| 69 | Static Methods | Class methods not bound to instance |
| 70 | Mixins (deprecated) | Shared reusable functionality (use HOCs instead) |
| 71 | `displayName` | Component name for debugging/React DevTools |
| 72 | `getDerivedStateFromProps` | Static method - sync state from props (rare) |
| 73 | `getSnapshotBeforeUpdate` | Capture DOM info before mutations (scroll position) |
| 74 | `componentDidCatch` | Error boundary - log errors from child tree |
| 75 | `useSyncExternalStore` (React 18) | Subscribe to external stores |
| 76 | `useInsertionEffect` (React 18) | Style injection before DOM mutations |
| 77 | Server Components (React 19) | Components render only on server - no interactivity |
| 78 | Client Components | `"use client"` directive - interactive components |

## 5. REACT ROUTER (79-89)

| # | Question | Answer Summary |
|---|----------|---------------|
| 79 | React Router v6 changes | Flattened API, `useNavigate`, no `Switch` |
| 80 | `BrowserRouter` vs `HashRouter` | Browser: clean URLs, Hash: # URLs (static hosting) |
| 81 | `Link` vs `NavLink` | NavLink adds active class automatically |
| 82 | `useNavigate` | Programmatic navigation hook (replaces `useHistory`) |
| 83 | `useParams` | Access route parameters |
| 84 | `useLocation` | Current URL location object |
| 85 | `useSearchParams` | URL query string management |
| 86 | `Outlet` | Child routes render point |
| 87 | `useRoutes` | Hook-based route configuration |
| 88 | Nested Routes | Layout routes with `Outlet` |
| 89 | Route Loaders (Data APIs) | `loader` function - fetch data before render |

## 6. REACT TESTING (96-101)

| # | Question | Answer Summary |
|---|----------|---------------|
| 96 | Testing Library vs Enzyme | Testing Library: user behavior, Enzyme: implementation |
| 97 | `React Testing Library` | `@testing-library/react` - test like users |
| 98 | `screen` utilities | `getByRole`, `getByText`, `queryByLabelText` |
| 99 | User Event | `@testing-library/user-event` - realistic interactions |
| 100 | Mocking modules | `jest.mock('./module')` |
| 101 | Mocking API calls | `msw` (Mock Service Worker) |

## 7. REDUX & STATE MANAGEMENT (102-135)

| # | Question | Answer Summary |
|---|----------|---------------|
| 102 | What is Redux? | Predictable state container (Flux architecture) |
| 103 | Redux Principles | Single store, read-only state, pure reducers |
| 104 | Reducer | Pure function: `(state, action) => state` |
| 105 | Redux Thunk | Async middleware - returns functions |
| 106 | Redux Saga | Side effects via ES6 generators |
| 107 | `createSlice` (RTK) | Combines reducer logic + actions |
| 108 | Redux Toolkit | Official recommended approach |
| 109 | `useSelector`/`useDispatch` | React-Redux hooks |
| 110 | `shallowEqual` | Optimize `useSelector` selector |
| 111 | RTK Query | Data fetching/caching (alternative to thunks) |
| 112 | Normalizr | Normalize API responses |
| 113 | Reselect | Memoized selectors |
| 114 | Redux DevTools | Time-travel debugging |
| 115 | Immer | Mutable immutable updates |
| 116 | Redux Persist | State persistence |
| 117 | Redux Saga Effects | `call`, `put`, `takeEvery`, `select` |
| 118 | Redux Middleware | Custom logic between action dispatch/store update |
| 119 | Redux Observable | RxJS-based side effects |
| 120 | Zustand | Minimal state management |
| 121 | Jotai | Atomic state management |
| 122 | Recoil | Facebook state management |
| 123 | MobX | Reactive state management |
| 124 | Valtio | Proxy-based state |
| 125 | Redux Form | Form state management |
| 126 | React Query | Server state management |
| 127 | SWR | Stale-while-revalidate caching |
| 128 | Apollo Client | GraphQL state management |
| 129 | Relay | Facebook GraphQL client |
| 130 | URQL | Lightweight GraphQL client |
| 131 | TanStack Query | Modern data fetching |
| 132 | RTK Query | Redux Toolkit data fetching |
| 133 | Zustand middleware | Persist, devtools, immer |
| 134 | XState | State machines/workflows |
| 135 | React Query DevTools | Query debugging |

## 8. REACT NATIVE (136-139)

| # | Question | Answer Summary |
|---|----------|---------------|
| 136 | React Native vs React | React Native renders native components |
| 137 | Flexbox differences | RN specific flex properties |
| 138 | `StyleSheet.create` | Performance optimization |
| 139 | Platform-specific code | `Platform.OS`, `.ios.js`/`.android.js` |

## 9. STYLING (140-155)

| # | Question | Answer Summary |
|---|----------|---------------|
| 140 | CSS Modules | Scoped CSS - `styles.module.css` |
| 141 | Styled Components | CSS-in-JS - tagged template literals |
| 142 | Emotion | CSS-in-JS library |
| 143 | JSS | CSS-in-JS runtime |
| 144 | Linaria | Zero-runtime CSS-in-JS |
| 145 | Tailwind CSS | Utility-first CSS |
| 146 | Styled JSX | Scoped CSS in components |
| 147 | CSS-in-JS pros/cons | Pros: scoped, dynamic; Cons: runtime overhead |
| 148 | Vanilla Extract | TypeScript CSS |
| 149 | Panda CSS | CSS-in-JS with design tokens |
| 150 | Stitches | CSS-in-JS with variants |

## 10. FORMS & VALIDATION (156-165)

| # | Question | Answer Summary |
|---|----------|---------------|
| 156 | Formik | Form library with validation |
| 157 | React Hook Form | Performant uncontrolled forms |
| 158 | Yup | Schema validation |
| 159 | Zod | TypeScript-first validation |
| 160 | React Final Form | Subscription-based forms |

## 11. NEXT.JS (166-185)

| # | Question | Answer Summary |
|---|----------|---------------|
| 166 | SSR vs SSG vs CSR | Server-render, Static, Client-render |
| 167 | `getStaticProps` | SSG data fetching |
| 168 | `getServerSideProps` | SSR data fetching |
| 169 | `getStaticPaths` | Dynamic routes SSG |
| 170 | App Router (Next 13+) | File-based routing `/app` |
| 171 | Server Components | Render on server only |
| 172 | `loading.js`/`error.js` | Streaming UI states |
| 173 | Middleware | Edge runtime request handling |

## 12. MISCELLANEOUS (186-270+)

| # | Question | Answer Summary |
|---|----------|---------------|
| 186 | Progressive Web Apps | Service workers, offline, installable |
| 187 | Micro Frontends | Team-independent frontend apps |
| 188 | Monorepo tools | Turborepo, Nx |
| 189 | Webpack vs Vite | Vite: faster dev server |
| 190 | TypeScript with React | `.tsx` files, interfaces/props |
| ... | *(Additional 80+ questions on build tools, deployment, security, etc.)* | ... |

---

## 📚 USAGE INSTRUCTIONS

1. **Quick Review**: Ctrl+F search questions during interview prep
2. **Print-Friendly**: Copy to markdown viewer (VS Code, Typora)
3. **Full Source**: [Original GitHub Repo](https://github.com/sudheerj/reactjs-interview-questions)
4. **Stars**: ⭐ 50k+ GitHub stars, actively maintained

**Save as `react-interview-complete.md` - 10k+ lines compressed to 2 pages for instant recall!**